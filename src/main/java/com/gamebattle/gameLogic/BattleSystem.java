package com.gamebattle.gameLogic;

/**
 *  Класс для описания работы механик битвы (методы атаки, уклонения и т.д.)
 */

import com.gamebattle.character.Barbarian;
import com.gamebattle.character.Rogue;
import com.gamebattle.character.Warrior;
import com.gamebattle.gameUtils.SleepTime;
import com.gamebattle.monsters.Monster;
import com.gamebattle.character.Character;
import com.gamebattle.weapon.Weapon;

import java.util.Scanner;

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
        SleepTime.sleepSeconds(1);
        System.out.println(player.getName() + " полностью восстанавливает здоровье!");
        SleepTime.sleep(300);
        System.out.println("Очки здоровья: " + oldPlayerHealth + " -> " + player.getHealth());
    }

    // метод предлагающий заменить оружие на новое после победы моба
    public static void offerWeaponDrop(Character player, Monster monster) {
        SleepTime.sleepSeconds(1);
        Weapon droppedWeapon = monster.getRewardForWinning();
        System.out.println("Монстр выбросил оружие: " + droppedWeapon.getName() + ".");
        SleepTime.sleep(300);

        Weapon currentWeapon = player.getMainClass().getStartWeapon();

        Scanner scanner = new Scanner(System.in);
        boolean choosing = true;

        while (choosing) {
            System.out.println("\nВыберите действие:");
            SleepTime.sleep(300);
            System.out.println("1. Показать характеристики выброшенного оружия");
            SleepTime.sleep(300);
            System.out.println("2. Сравнить со своим текущим оружием");
            SleepTime.sleep(300);
            System.out.println("3. Заменить оружие");
            SleepTime.sleep(300);
            System.out.println("4. Продолжить со своим оружием");
            SleepTime.sleep(300);

            System.out.print("\nВаш выбор: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showWeaponStats(droppedWeapon, "Выброшенное оружие");
                    break;

                case "2":
                    compareWeapons(currentWeapon, droppedWeapon);
                    break;

                case "3":
                    if (replaceWeapon(player, currentWeapon, droppedWeapon)) {
                        choosing = false;
                    }
                    break;

                case "4":
                    System.out.println("\nВы решили оставить своё текущее оружие.");
                    SleepTime.sleep(300);
                    choosing = false;
                    break;

                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите от 1 до 4.");
                    SleepTime.sleep(300);
            }
        }
    }

    // Метод для показа характеристик оружия
    private static void showWeaponStats(Weapon weapon, String title) {
        System.out.println("\n" + title + ":");
        System.out.println("+------------------------+");
        System.out.printf("| Название: %-12s |\n", weapon.getName());
        System.out.printf("| Урон: %-16d |\n", weapon.getDamage());
        System.out.printf("| Тип урона: %-10s |\n", weapon.getDamageType());
        System.out.println("+------------------------+");
        SleepTime.sleep(500);
    }

    // Метод для сравнения оружий
    private static void compareWeapons(Weapon current, Weapon newWeapon) {
        System.out.println("\nСравнение оружия:");
        System.out.println("+------------------------+------------------------+");
        System.out.printf("| %-22s | %-22s |\n", "Текущее оружие", "Новое оружие");
        System.out.println("+------------------------+------------------------+");
        System.out.printf("| Название: %-12s | Название: %-12s |\n",
                current.getName(), newWeapon.getName());
        System.out.printf("| Урон: %-16d | Урон: %-16d |\n",
                current.getDamage(), newWeapon.getDamage());
        System.out.printf("| Тип: %-17s | Тип: %-17s |\n",
                current.getDamageType(), newWeapon.getDamageType());
        System.out.println("+------------------------+------------------------+");

        // Показываем разницу в уроне
        int damageDifference = newWeapon.getDamage() - current.getDamage();
        if (damageDifference > 0) {
            System.out.printf("Новое оружие лучше на %d урона!%n", damageDifference);
        } else if (damageDifference < 0) {
            System.out.printf("Текущее оружие лучше на %d урона%n", Math.abs(damageDifference));
        } else {
            System.out.println("Оружия имеют одинаковый урон");
        }
        SleepTime.sleep(500);
    }

    // Метод для замены оружия
    private static boolean replaceWeapon(Character player, Weapon currentWeapon, Weapon newWeapon) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nВы уверены, что хотите заменить оружие?");
        System.out.println("Текущее: " + currentWeapon.getName() + " (урон: " + currentWeapon.getDamage() + ")");
        System.out.println("Новое: " + newWeapon.getName() + " (урон: " + newWeapon.getDamage() + ")");
        SleepTime.sleep(300);
        System.out.print("Подтвердите замену (да/нет): ");

        String confirmation = scanner.nextLine().toLowerCase();

        if (confirmation.equals("да") || confirmation.equals("д") ||
                confirmation.equals("yes") || confirmation.equals("y")) {

            player.replaceWeapon(newWeapon);

            System.out.println("\nОружие успешно заменено!");
            System.out.println("Теперь вы используете: " + newWeapon.getName());
            SleepTime.sleep(300);
            return true;
        } else {
            System.out.println("Замена отменена.");
            SleepTime.sleep(300);
            return false;
        }
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
        SleepTime.sleep(300);
        System.out.println("-------------\n" + "Атака  игрока\n" + "-------------");

        // Сбрасываем временный бонус перед расчетом новой атаки
        player.resetDamageBonus();

        // 1. Вычисляем шанс попадания
        int totalAgility = player.getAgility() + monster.getAgility();
        int randomRoll = (int) (Math.random() * totalAgility) + 1;

        if (randomRoll <= monster.getAgility()) {
            SleepTime.sleep(300);
            System.out.println(player.getName() + " промахивается! (выпало: " + randomRoll + ", нужно больше "
                    + monster.getAgility() + ")\n");
            SleepTime.sleep(300);
            return false;
        }
        else {
            // 2. Применяем временные бонусы
            applyClassDamageBonuses(player, monster, turnCount);

            // 3. Считаем итоговый урон
            int totalDamage = player.getTotalDamage();
            double oldMonsterHealth = monster.getHealth();

            SleepTime.sleep(300);
            System.out.println(player.getName() + " атакует " + monster.getName() + " и наносит " + totalDamage +
                    " урона! (выпало: " + randomRoll + ")");

            // 4. Вычитаем урон
            monster.takeDamage(totalDamage);
            SleepTime.sleep(300);
            System.out.println("\nОчки здоровья монстра: " + oldMonsterHealth + " --> " + monster.getHealth() +"\n");
            SleepTime.sleep(300);

            if (!monster.isAlive()) {
                SleepTime.sleep(300);
                System.out.println("--------------------------------------------");
                System.out.println(monster.getName() + " побеждён!");
                System.out.println("--------------------------------------------\n");
                SleepTime.sleepSeconds(2);
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
        // Для воина
        if (player.getMainClass() instanceof Warrior) {
            Warrior warriorClass = (Warrior) player.getMainClass();
            warriorClass.applyDamageBonuses(player, monster, turnCount);
        }
//
        // Для варвара
        if (player.getMainClass() instanceof Barbarian) {
            Barbarian barbarianClass = (Barbarian) player.getMainClass();
            barbarianClass.applyDamageBonuses(player, monster, turnCount);
        }
    }

    //  метод для атаки монстра
    public static boolean monsterAttack(Monster monster, Character player, int turnCounter) {
        SleepTime.sleep(300);
        System.out.println("-------------\n" + "Атака монстра\n" + "-------------");

        // 1. Вычисляем шанс попадания: случайное число от 1 до суммы ловкости атакующего и цели
        int totalAgility = monster.getAgility() + player.getAgility();
        int randomRoll = (int) (Math.random() * totalAgility) + 1;

        // Если число меньше или равно ловкости цели - атака промахнулась
        if (randomRoll <= player.getAgility()) {
            SleepTime.sleep(300);
            System.out.println("Монстр " + monster.getName() + " промахивается! (выпало: " + randomRoll +
                    ", нужно больше " + player.getAgility() + ")\n");
            SleepTime.sleep(300);
            return false;
        }
        // Иначе - попадание
        else {
            // 2. Считаем изначальный урон:
            int baseDamage = monster.getDamage();
            double oldPlayerHealth = player.getHealth();

            // 3. Применяем бонусы защиты (щит воина если есть)
            int finalDamage = applyDefenseBonuses(player, monster, baseDamage);

            SleepTime.sleep(300);
            System.out.println("Монстр " + monster.getName() + " атакует " + player.getName() + " и наносит " +
                    finalDamage + " урона! (выпало: " + randomRoll + ")");

            // 4. Вычитаем урон из здоровья цели
            player.takeDamage(finalDamage);
            SleepTime.sleep(300);
            System.out.println("\nОчки здоровья игрока: " + oldPlayerHealth + " --> " + player.getHealth() + "\n");
            SleepTime.sleep(300);

            // 5. Проверяем, не умерла ли цель
            if (!player.isAlive()) {
                SleepTime.sleepSeconds(300);
                System.out.println("--------------------------------------------");
                System.out.println(player.getName() + " был побеждён монстром " + monster.getName() + "!");
                System.out.println("--------------------------------------------\n");
                SleepTime.sleepSeconds(2);
            }

            return true;
        }
    }

    // Метод для применения бонусов защиты
    private static int applyDefenseBonuses(Character player, Monster attacker, int incomingDamage) {
        int finalDamage = incomingDamage;

        // Для воина (щит)
        if (player.getMainClass() instanceof Warrior) {
            Warrior warriorClass = (Warrior) player.getMainClass();
            finalDamage = warriorClass.applyShieldBonus(player, attacker, finalDamage);
        }

        // Для варвара (каменная кожа)
        if (player.getMainClass() instanceof Barbarian) {
            Barbarian barbarianClass = (Barbarian) player.getMainClass();
            finalDamage = barbarianClass.applyRockSkinBonus(player, attacker, finalDamage);
        }

        return finalDamage;
    }
}