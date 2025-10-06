package com.gamebattle.character;

/**
 * Класс для работы с уровнем персонажа и мультиклассом
 */

import java.util.Scanner;

public class LevelManager {

    private static final Scanner scanner = new Scanner(System.in);

    // повышение уровня персонажа
    public static void levelUpCharacter(Character character) {
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

        System.out.printf("""
                    +------------------------------------------+
                    |        ПОВЫШЕНИЕ УРОВНЯ ПЕРСОНАЖА        |
                    +------------------------------------------+
                    | %-40s |
                    | Уровень: %-31d |
                    +------------------------------------------+
                    %n""",
                displayName + " повысил уровень!",
                character.getCharacterLevel()
        );
    }

    // повышение уровня класса (мультикласс)
    public static void levelUpClass(Character character) {
        System.out.println("Вам доступно повышение класса!");
        System.out.print("""
                         +------------------------------------------+
                         |       ВЫБОР КЛАССА ДЛЯ ПОВЫШЕНИЯ         |
                         +------------------------------------------+
                         """
        );

        if (character.getCharacterLevel() <= 0) {
            System.out.println("Сначала повысьте уровень персонажа!");
            return;
        }

        // текущие классы
        System.out.println("Текущие классы персонажа:");
        for (CharacterClassLevel classLevel : character.getClasses()) {
            System.out.println("- " + classLevel.getCharacterClass().getClassName() + " (уровень " + classLevel.getLevel() + ")");
        }

        CharacterClass chosenClass = chooseClassForLevelUp();
        applyClassLevelUp(character, chosenClass);
    }

    private static void applyClassLevelUp(Character character, CharacterClass chosenClass) {
        CharacterClassLevel existingClass = character.findClass(chosenClass);

        boolean isNewClass = (existingClass == null);

        if (existingClass != null) {
            // Повышаем уровень существующего класса
            character.increaseClassLevel(chosenClass);
            int newLevel = existingClass.getLevel();

            System.out.println("\n" + character.getName() + " повысил класс " + chosenClass.getClassName() + " до уровня "
                    + newLevel + "!");
            applyClassBonuses(character, chosenClass, newLevel, isNewClass);

        } else {
            // Добавляем новый класс
            character.addClass(chosenClass, 1);
            System.out.println("\n" + character.getName() + " изучил новый класс: " + chosenClass.getClassName() + "!");
            applyClassBonuses(character, chosenClass, 1, isNewClass);
        }
    }

    private static void applyClassBonuses(Character character, CharacterClass characterClass, int level, boolean isNewClass) {

        if (isNewClass) {
            // новый класс (мультикласс) - добавляем базовое здоровье
            double baseHealth = characterClass.getBaseHealth();
            character.applyHealthBonus(baseHealth);
            System.out.println("Награда за новый класс: +" + baseHealth + " к здоровью");
        } else if (level > 1) {
            // повышение уровня существующего класса - добавляем здоровье за уровень
            double healthBonus = characterClass.getHealthUpByClassLevel(character);
            character.applyHealthBonus(healthBonus);
            System.out.println("Награда за уровень класса: +" + healthBonus + " к здоровью");
        }

        // Специфичные бонусы класса
        if (level == 1) {
            characterClass.useClassBonusLevelOne(character);
            System.out.println();
        } else if (level == 2) {
            characterClass.useClassBonusLevelTwo(character);
            System.out.println();
        } else if (level == 3) {
            characterClass.useClassBonusLevelThree(character);
            System.out.println();
        }

        System.out.println("Отладка: Текущее здоровье = " + character.getHealth());
        System.out.println("Отладка: Максимальное здоровье = " + character.getMaxHealth());
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