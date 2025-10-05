package com.gamebattle.character;

/**
 * Класс для работы с уровнем персонажа и мультиклассом
 */

import java.util.Scanner;

public class LevelManager {

    private static final Scanner scanner = new Scanner(System.in);

    // Повышение уровня персонажа
    public static void levelUpCharacter(Character character) {
        System.out.println("=== ПОВЫШЕНИЕ УРОВНЯ ПЕРСОНАЖА ===");

        if (character.getCharacterLevel() >= 3) {
            System.out.println("Персонаж " + character.getName() + " уже достиг максимального уровня!");
            return;
        }

        character.increaseCharacterLevel();

        String displayName;
        if (character.getName().isEmpty() || character.getName().equals("Unnamed")) {
            displayName = "Игровой персонаж";
        } else {
            displayName = character.getName();
        }

        System.out.println(displayName + " повысил уровень до " + character.getCharacterLevel() + "!");
    }

    // Повышение уровня класса (мультикласс)
    public static void levelUpClass(Character character) {
        System.out.println("=== ВЫБОР КЛАССА ДЛЯ ПОВЫШЕНИЯ ===");

        if (character.getCharacterLevel() <= 0) {
            System.out.println("Сначала повысьте уровень персонажа!");
            return;
        }

        // Показываем текущие классы
        System.out.println("Текущие классы персонажа:");
        for (CharacterClassLevel classLevel : character.getClasses()) {
            System.out.println("- " + classLevel.getCharacterClass().getClassName() + " (уровень " + classLevel.getLevel() + ")");
        }

        CharacterClass chosenClass = chooseClassForLevelUp();
        applyClassLevelUp(character, chosenClass);
    }

    private static void applyClassLevelUp(Character character, CharacterClass chosenClass) {
        CharacterClassLevel existingClass = character.findClass(chosenClass);

        if (existingClass != null) {
            // Повышаем уровень существующего класса
            character.increaseClassLevel(chosenClass);
            int newLevel = existingClass.getLevel();

            System.out.println(character.getName() + " повысил класс " + chosenClass.getClassName() + " до уровня "
                    + newLevel + "!");
            applyClassBonuses(character, chosenClass, newLevel);

        } else {
            // Добавляем новый класс
            character.addClass(chosenClass, 1);
            System.out.println(character.getName() + " изучил новый класс: " + chosenClass.getClassName() + "!");
            applyClassBonuses(character, chosenClass, 1);
        }
    }

    private static void applyClassBonuses(Character character, CharacterClass characterClass, int level) {
        // Бонус здоровья за уровень класса
        double healthBonus = characterClass.getHealthUpByClassLevel(character);
        character.applyHealthBonus(healthBonus);
        System.out.println("Награда за уровень класса: +" + healthBonus + " к здоровью\n");


        // Специфичные бонусы класса
        if (level == 1) {
            characterClass.useClassBonusLevelOne(character);
        } else if (level == 2) {
            characterClass.useClassBonusLevelTwo(character);
        } else if (level == 3) {
            characterClass.useClassBonusLevelThree(character);
        }
    }

    public static CharacterClass chooseClassForLevelUp() {
        System.out.println("\nВыберите класс для повышения уровня:");
        System.out.println("1. Разбойник");
        System.out.println("2. Воин");
        System.out.println("3. Варвар");
        System.out.print("\nВаш выбор (1-3): ");

        while (true) {
            try {
                int inputNumber = scanner.nextInt();
                scanner.nextLine();

                switch (inputNumber) {
                    case 1:
                        return new Rogue();
                    case 2:
                        return new Warrior();
                    case 3:
                        return new Barbarian();
                    default:
                        System.out.print("Неверный выбор. Пожалуйста, введите 1, 2 или 3: ");
                }
            } catch (Exception e) {
                System.out.print("Неверный ввод. Пожалуйста, введите число 1, 2 или 3: ");
                scanner.nextLine();
            }
        }
    }
}