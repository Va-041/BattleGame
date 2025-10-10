//    package com.gamebattle.character;
//
//    /**
//     * Класс для работы с уровнем персонажа и мультиклассом
//     */
//
//    import java.util.Scanner;
//
//    public class LevelManager {
//
//        private static final Scanner scanner = new Scanner(System.in);
//
//        // повышение уровня персонажа
//        public static void levelUpCharacter(Character character) {
//            if (character.getCharacterLevel() >= 3) {
//                System.out.println("Персонаж " + character.getName() + " уже достиг максимального уровня!");
//                return;
//            }
//
//            character.increaseCharacterLevel();
//
//            String displayName;
//            if (character.getName().isEmpty() || character.getName().equals("Unnamed")) {
//                displayName = "Игровой персонаж";
//            } else {
//                displayName = character.getName();
//            }
//
//            System.out.printf("""
//                        +------------------------------------------+
//                        |        ПОВЫШЕНИЕ УРОВНЯ ПЕРСОНАЖА        |
//                        +------------------------------------------+
//                        | %-40s |
//                        | Уровень: %-31d |
//                        +------------------------------------------+
//                        %n""",
//                    displayName + " повысил уровень!",
//                    character.getCharacterLevel()
//            );
//        }
//
//        // повышение уровня класса (мультикласс)
//        public static void levelUpClass(Character character) {
//            System.out.println("Вам доступно повышение класса!");
//            System.out.print("""
//                             +------------------------------------------+
//                             |       ВЫБОР КЛАССА ДЛЯ ПОВЫШЕНИЯ         |
//                             +------------------------------------------+
//                             """
//            );
//
//            if (character.getCharacterLevel() <= 0) {
//                System.out.println("Сначала повысьте уровень персонажа!");
//                return;
//            }
//
//            // текущие классы
//            System.out.println("Текущие классы персонажа:");
//            for (CharacterClassLevel classLevel : character.getClasses()) {
//                System.out.println("- " + classLevel.getCharacterClass().getClassName() + " (уровень " + classLevel.getLevel() + ")");
//            }
//
//            CharacterClass chosenClass = chooseClassForLevelUp();
//            applyClassLevelUp(character, chosenClass);
//        }
//
//        private static void applyClassLevelUp(Character character, CharacterClass chosenClass) {
//            CharacterClassLevel existingClass = character.findClass(chosenClass);
//            boolean isNewClass = (existingClass == null);
//
//            if (existingClass != null) {
//                // Повышаем уровень существующего класса
//                character.increaseClassLevel(chosenClass);
//                int newLevel = existingClass.getLevel();
//
//                CharacterClass actualClass = existingClass.getCharacterClass();
//
//                System.out.println("\n" + character.getName() + " повысил класс " + actualClass.getClassName() + " до уровня "
//                        + newLevel + "!");
//                applyClassBonuses(character, actualClass, newLevel, isNewClass);
//            } else {
//                // Добавляем новый класс
//                character.addClass(chosenClass, 1);
//                System.out.println("\n" + character.getName() + " изучил новый класс: " + chosenClass.getClassName() + "!");
//                applyClassBonuses(character, chosenClass, 1, isNewClass);
//            }
//        }
//
//        private static void applyClassBonuses(Character character, CharacterClass characterClass, int level, boolean isNewClass) {
//
//            if (isNewClass) {
//                // новый класс (мультикласс) - добавляем базовое здоровье
//                double baseHealth = characterClass.getBaseHealth();
//                character.applyHealthBonus(baseHealth);
//                System.out.println("Награда за новый класс: +" + baseHealth + " к здоровью");
//            } else if (level > 1) {
//                // повышение уровня существующего класса - добавляем здоровье за уровень
//                double healthBonus = characterClass.getHealthUpByClassLevel(character);
//                character.applyHealthBonus(healthBonus);
//                System.out.println("Награда за уровень класса: +" + healthBonus + " к здоровью");
//            }
//
//            // Специфичные бонусы класса
//            if (level == 1) {
//                characterClass.useClassBonusLevelOne(character);
//                System.out.println();
//            } else if (level == 2) {
//                characterClass.useClassBonusLevelTwo(character);
//                System.out.println();
//            } else if (level == 3) {
//                characterClass.useClassBonusLevelThree(character);
//                System.out.println();
//            }
//        }
//
//        public static CharacterClass chooseClassForLevelUp() {
//            System.out.println("\nВыберите класс для повышения уровня:");
//            System.out.println("1. Разбойник");
//            System.out.println("2. Воин");
//            System.out.println("3. Варвар");
//            System.out.print("\nВаш выбор (1-3): ");
//
//            while (true) {
//                try {
//                    int inputNumber = scanner.nextInt();
//                    scanner.nextLine();
//
//                    switch (inputNumber) {
//                        case 1:
//                            return new Rogue();
//                        case 2:
//                            return new Warrior();
//                        case 3:
//                            return new Barbarian();
//                        default:
//                            System.out.print("Неверный выбор. Пожалуйста, введите 1, 2 или 3: ");
//                    }
//                } catch (Exception e) {
//                    System.out.print("Неверный ввод. Пожалуйста, введите число 1, 2 или 3: ");
//                    scanner.nextLine();
//                }
//            }
//        }
//    }

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
        if (getTotalClassLevels(character) >= 3) {
            System.out.println("Достигнут максимальный суммарный уровень классов (3)!");
            System.out.println("Невозможно добавить новые уровни классов.");
            return;
        }

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

        System.out.println("Суммарный уровень классов: " + getTotalClassLevels(character) + "/3");

        CharacterClass chosenClass = chooseClassForLevelUp(character);
        applyClassLevelUp(character, chosenClass);
    }

    // Получить суммарный уровень всех классов
    private static int getTotalClassLevels(Character character) {
        int totalLevels = 0;
        for (CharacterClassLevel classLevel : character.getClasses()) {
            totalLevels += classLevel.getLevel();
        }
        return totalLevels;
    }

    // Проверить, можно ли повысить выбранный класс
    private static boolean canLevelUpClass(Character character, CharacterClass chosenClass) {
        CharacterClassLevel existingClass = character.findClass(chosenClass);

        // Если класс новый - проверяем, есть ли место для нового класса
        if (existingClass == null) {
            return getTotalClassLevels(character) < 3;
        }

        // Если класс существует - проверяем, не достиг ли он максимального уровня
        // и есть ли свободные уровни
        return existingClass.getLevel() < 3 && getTotalClassLevels(character) < 3;
    }

    private static void applyClassLevelUp(Character character, CharacterClass chosenClass) {
        CharacterClassLevel existingClass = character.findClass(chosenClass);
        boolean isNewClass = (existingClass == null);

        // Проверяем, можно ли повысить класс
        if (!canLevelUpClass(character, chosenClass)) {
            System.out.println("Невозможно повысить этот класс! Достигнут лимит уровней.");
            return;
        }

        if (existingClass != null) {
            // Повышаем уровень существующего класса
            character.increaseClassLevel(chosenClass);
            int newLevel = existingClass.getLevel();

            CharacterClass actualClass = existingClass.getCharacterClass();

            System.out.println("\n" + character.getName() + " повысил класс " + actualClass.getClassName() + " до уровня "
                    + newLevel + "!");
            applyClassBonuses(character, actualClass, newLevel, isNewClass);
        } else {
            // Добавляем новый класс
            character.addClass(chosenClass, 1);
            System.out.println("\n" + character.getName() + " изучил новый класс: " + chosenClass.getClassName() + "!");
            applyClassBonuses(character, chosenClass, 1, isNewClass);
        }

        System.out.println("Текущий суммарный уровень классов: " + getTotalClassLevels(character) + "/3");
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

        // бонусы класса
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
    }

    public static CharacterClass chooseClassForLevelUp(Character character) {
        System.out.println("\nВыберите класс для повышения уровня:");
        System.out.println("1. Разбойник");
        System.out.println("2. Воин");
        System.out.println("3. Варвар");
        System.out.print("\nВаш выбор (1-3): ");

        while (true) {
            try {
                int inputNumber = scanner.nextInt();
                scanner.nextLine();

                CharacterClass chosenClass;
                switch (inputNumber) {
                    case 1:
                        chosenClass = new Rogue();
                        break;
                    case 2:
                        chosenClass = new Warrior();
                        break;
                    case 3:
                        chosenClass = new Barbarian();
                        break;
                    default:
                        System.out.print("Неверный выбор. Пожалуйста, введите 1, 2 или 3: ");
                        continue;
                }

                // можно ли выбрать этот класс
                if (!canLevelUpClass(character, chosenClass)) {
                    CharacterClassLevel existingClass = character.findClass(chosenClass);
                    if (existingClass != null && existingClass.getLevel() >= 3) {
                        System.out.print("Этот класс уже достиг максимального уровня (3). Выберите другой класс: ");
                    } else {
                        System.out.print("Достигнут максимальный суммарный уровень (3). Невозможно добавить новые уровни. Выберите другой класс: ");
                    }
                    continue;
                }

                return chosenClass;

            } catch (Exception e) {
                System.out.print("Неверный ввод. Пожалуйста, введите число 1, 2 или 3: ");
                scanner.nextLine();
            }
        }
    }

    // Метод для проверки доступности повышения классов
    public static boolean canLevelUpAnyClass(Character character) {
        return getTotalClassLevels(character) < 3;
    }
}