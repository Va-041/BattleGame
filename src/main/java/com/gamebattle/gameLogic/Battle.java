package com.gamebattle.gameLogic;

import com.gamebattle.character.Character;
import com.gamebattle.monsters.Monster;

public class Battle {

    public static BattleResult startBattle(Character player, Monster monster) {
        System.out.println("=======================================================");
        System.out.println("\t\t\t⚔️ Начинается бой с " + monster.getName() + "!");
        System.out.println("=======================================================");

        // кто ходит первым
        boolean playerFirst = determineFirstTurn(player, monster);

        BattleResult result = new BattleResult();

        while (player.isAlive() && monster.isAlive()) {
            // Увеличиваем счетчик ходов в начале каждого раунда
            result.incrementTurnCount();
            System.out.println("🎲 Ход " + result.getTurnCount());

            if (playerFirst) {
                playerTurn(player, monster, result);
                if (!monster.isAlive()) {
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
        boolean hit = BattleSystem.playerAttack(player, monster, result.getTurnCount());
        result.addPlayerAttack(hit);
    }

    private static void monsterTurn(Monster monster, Character player, BattleResult result) {
        boolean hit = BattleSystem.monsterAttack(monster, player, result.getTurnCount());
        result.addMonsterAttack(hit);
    }
}