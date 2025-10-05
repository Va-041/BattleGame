package com.gamebattle.gameLogic;

/**
 *  Класс для описания логики боя
 */

import com.gamebattle.character.Character;
import com.gamebattle.monsters.Monster;
import static com.gamebattle.gameLogic.BattleSystem.calculateEvasionChance;
import static com.gamebattle.gameLogic.BattleSystem.calculateHitChance;
import static com.gamebattle.gameLogic.BattleSystem.calculateMonsterEvasionChance;
import static com.gamebattle.gameLogic.BattleSystem.calculateMonsterHitChance;

public class Battle {

//    public static void startBattle(Character player, LocationAndLore.Location location) {
//
//
//        Monster monster = location.monster;
//
//        System.out.println("⚔️ Начинается бой с " + monster.getName() + "!");
//
//        // Боевая логика
//        while (player.isAlive() && monster.isAlive()) {
//
//            // 1. Обращаемся к Системе битвы и узнаем кто ходит первым (у кого выше ловкость тот и первый ходит)
//            // 2. тот кто ходит делает атаку.
//            //
//
//            // Ход игрока
//            BattleSystem.playerAttack(player,monster);
//            if (!monster.isAlive()) {
//                System.out.println("🎉 Вы победили " + monster.getName() + "!");
//
//                // Можно добавить логику добавления оружия в инвентарь игрока
//                // player.addToInventory(reward);
//                victoryBattle(player);
//                break;
//            }
//
//            // Ход монстра
//            BattleSystem.monsterAttack(monster,player);
//            if (!player.isAlive()) {
//                System.out.println("💀 Вы погибли...");
//                defeatBattle(player);
//                break;
//            }
//        }
//    }

//    // Если победа в битве
//    public static void victoryBattle(Character player) {
//
//            monstersDefeated++;
//            BattleSystem.checkVictory(monstersDefeated);
//            BattleSystem.restoreHealth(player);
//
//            LevelManager.levelUpCharacter(player);
//            LevelManager.levelUpClass(player);
//
//            printFinalCharacterInfo(player);
//
//            // следующая лока
//            while (LocationNavigation.getRemainingLocationsCount() > 0) {
//                System.out.println("-------------------------------------------------------");
//                System.out.println("|| Нажмите Enter для перехода к следующей локации... ||");
//                System.out.println("-------------------------------------------------------");
//                try {
//                    System.in.read();
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//
//                LocationNavigation.getNextLocations();
//                LocationNavigation.displayCurrentLocations();
//
//                Battle.startBattle(player, currentLocation);
//
//                // если победа - вызов метода повышения уровней, вызов printFinal
//            }
//    }
//
//    public static void defeatBattle(Character player) {
//
//    }

    public static BattleResult startBattle(Character player, Monster monster) {
        System.out.println("=======================================================");
        System.out.println("\t\t\t⚔️ Начинается бой с " + monster.getName() + "!");
        System.out.println("=======================================================");
        showBattleInfo(player, monster);

        // Определяем кто ходит первым
        boolean playerFirst = determineFirstTurn(player, monster);


        BattleResult result = new BattleResult();

        while (player.isAlive() && monster.isAlive()) {
            if (playerFirst) {
                playerTurn(player, monster, result);
                if (!monster.isAlive()) {
                    System.out.println("------------------------------------");
                    System.out.println(monster.getName() + " был повержен!");
                    System.out.println("------------------------------------");
                    break;
                }


                monsterTurn(monster, player, result);
            } else {
                monsterTurn(monster, player, result);
                if (!player.isAlive()) break;
                playerTurn(player, monster, result);
            }
        }

        result.setVictory(player.isAlive());
        return result;
    }

    private static boolean determineFirstTurn(Character player, Monster monster) {
        if (player.getAgility() > monster.getAgility()) {
            System.out.println("\nПервым начинает ход " + player.getName() + "!\n");
            return true;
        } else if (monster.getAgility() > player.getAgility()) {
            System.out.println("\nПервым начинает ход " + monster.getName() + "!\n");
            return false;
        } else {
            System.out.println("\nПервым начинает ход " + player.getName() + "!\n");
            return true;
        }
    }

    private static void playerTurn(Character player, Monster monster, BattleResult result) {
        boolean hit = BattleSystem.playerAttack(player, monster);
        result.addPlayerAttack(hit);
    }

    private static void monsterTurn(Monster monster, Character player, BattleResult result) {
        boolean hit = BattleSystem.monsterAttack(monster, player);
        result.addMonsterAttack(hit);
    }

    public static void showBattleInfo(Character player, Monster monster) {
        double playerHitChance = calculateHitChance(player, monster);
        double playerEvasionChance = calculateEvasionChance(player, monster);
        double monsterHitChance = calculateMonsterHitChance(monster, player);
        double monsterEvasionChance = calculateMonsterEvasionChance(monster, player);

        System.out.printf("""
                ИНФОРМАЦИЯ О БОЕ:

                %s:
                - Урон: %d
                - Шанс попадания: %.1f%%
                - Шанс уклонения: %.1f%%

                %s:
                - Урон: %d
                - Шанс попадания: %.1f%%
                - Шанс уклонения: %.1f%%
                %n""",
                player.getName(), player.getTotalDamage(), playerHitChance, playerEvasionChance,
                monster.getName(), monster.getWeaponDamage(), monsterHitChance, monsterEvasionChance
        );
        System.out.println("-------------------------------------------------------");
    }
}
