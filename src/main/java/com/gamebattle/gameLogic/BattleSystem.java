package com.gamebattle.gameLogic;

/**
 *  Класс для описания работы механик битвы (методы атаки, уклонения и т.д.)
 */

import com.gamebattle.character.Rogue;
import com.gamebattle.monsters.Monster;
import com.gamebattle.character.Character;

public class BattleSystem {

    public static int monstersDefeated = 0;


    // метод победы если было побеждено 5 монстров подряд
    public static boolean checkVictory( Character player) {
        if (monstersDefeated == 5) {
            return StartGame.gameIsOver(player);
        }
        return false;
    }

    // метод восстанавливающий хп до максимума при победе
    public static void restoreHealth(Character player) {
        double oldPlayerHealth = player.getHealth();
        player.restoreHealth();
        System.out.println(player.getName() + " полностью восстанавливает здоровье!");
        System.out.println("Очки здоровья: " + oldPlayerHealth + " -> " + player.getHealth());
    }

    // метод предлагающий заменить оружие на новое после победы моба
    public static void offerWeaponDrop(Character player, Monster monster) {
        System.out.println("Монстр выбросил оружие: " + monster.getRewardForWinning().getName() + ".");
        // логика замены оружия
        System.out.println("1. Показать характеристики");
        System.out.println("2. Сравнить со своим");
        System.out.println("3. Заменить ");

        System.out.println();
    }

    // метод возвращающий игрока к созданию персонажа при поражении
    public static void playerDefeated() {

        // если персонаж побежден: сбрасываем пройденные локации, сбрасываем персонажа, сбрасываем счетчик побежденных
        // монстров, если были.
        // Спрашивем хочет попробовать ещё раз если да то отправляем на создание персонажа. Если нет выход.

        LocationNavigation.resetUsedLocations();
        // возврат к созданию персонажа
    }



    // метод для атаки игрока
    public static boolean playerAttack(Character player, Monster monster, int turnCount) {
        System.out.println("-------------\n" + "Атака  игрока\n" + "-------------");

        // Сбрасываем временный бонус перед расчетом новой атаки
        player.resetDamageBonus();

        // 1. Вычисляем шанс попадания
        int totalAgility = player.getAgility() + monster.getAgility();
        int randomRoll = (int) (Math.random() * totalAgility) + 1;

        if (randomRoll <= monster.getAgility()) {
            System.out.println(player.getName() + " промахивается! (выпало: " + randomRoll + ", нужно больше "
                    + monster.getAgility() + ")\n");
            return false;
        }
        else {
            // 2. Применяем временные бонусы
            applyClassDamageBonuses(player, monster, turnCount);

            // 3. Считаем итоговый урон (уже включает временные бонусы)
            int totalDamage = player.getTotalDamage();
            double oldMonsterHealth = monster.getHealth();

            System.out.println(player.getName() + " атакует " + monster.getName() + " и наносит " + totalDamage +
                    " урона! (выпало: " + randomRoll + ")");

            // 4. Вычитаем урон
            monster.takeDamage(totalDamage);
            System.out.println("\nОчки здоровья монстра: " + oldMonsterHealth + " --> " + monster.getHealth() +"\n");


            if (!monster.isAlive()) {
                System.out.println("--------------------------------------------");
                System.out.println(monster.getName() + " побеждён!");
                System.out.println("--------------------------------------------\n");
            }

            return true;
        }
    }
    // Метод для применения бонусов урона от всех классов
    private static void applyClassDamageBonuses(Character player, Monster monster, int turnCount) {
        // Для разбойника
        if (player.getMainClass() instanceof Rogue) {
            Rogue rogueClass = (Rogue) player.getMainClass();
            rogueClass.applyDamageBonuses(player, monster, turnCount);
        }
//        // Для воина
//        if (player.getMainClass() instanceof Warrior) {
//            Warrior warriorClass = (Warrior) player.getMainClass();
//            warriorClass.applyDamageBonuses(player, monster, turnCount);
//        }
//
//        // Для варвара
//        if (player.getMainClass() instanceof Barbarian) {
//            Barbarian barbarianClass = (Barbarian) player.getMainClass();
//            barbarianClass.applyDamageBonuses(player, monster, turnCount);
//        }
    }

    //  метод для атаки монстра
    public static boolean monsterAttack(Monster monster, Character player, int turnCounter) {
        System.out.println("-------------\n" + "Атака монстра\n" + "-------------");

        // 1. Вычисляем шанс попадания: случайное число от 1 до суммы ловкости атакующего и цели
        int totalAgility = monster.getAgility() + player.getAgility();
        int randomRoll = (int) (Math.random() * totalAgility) + 1;

        // Если число меньше или равно ловкости цели - атака промахнулась
        if (randomRoll <= player.getAgility()) {
            System.out.println("Монстр " + monster.getName() + " промахивается! (выпало: " + randomRoll +
                    ", нужно больше " + player.getAgility() + ")\n");
            return false;
        }
        // Иначе - попадание
        else {
            // 2. Считаем изначальный урон: урон оружия атакующего
            int baseDamage = monster.getDamage();
            double oldPlayerHealth = player.getHealth();

            System.out.println("Монстр " + monster.getName() + " атакует " + player.getName() + " и наносит " +
                    baseDamage + " урона! (выпало: " + randomRoll + ")");

            // 3. Вычитаем урон из здоровья цели
            player.takeDamage(baseDamage);
            System.out.println("\nОчки здоровья игрока: " + oldPlayerHealth + " --> " + player.getHealth() + "\n");

            // 4. Проверяем, не умерла ли цель
            if (!player.isAlive()) {

                System.out.println("--------------------------------------------");
                System.out.println(player.getName() + " был побеждён монстром " + monster.getName() + "!");
                System.out.println("--------------------------------------------\n");
            }

            return true;
        }
    }
}