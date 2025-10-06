package com.gamebattle.gameLogic;

/**
 *  Класс для создания игрового персонажа
 */

import com.gamebattle.character.Barbarian;
import com.gamebattle.character.Character;
import com.gamebattle.character.CharacterClass;
import com.gamebattle.character.Rogue;
import com.gamebattle.character.Warrior;
import com.gamebattle.customExceptions.CharacterCreationException;
import java.util.Random;
import java.util.Scanner;

public class CreateCharacter {

    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    public static Character createNewCharacter() throws CharacterCreationException {
        try {
            String characterName = getCharacterName();
            CharacterClass characterClass = chooseCharacterClass();

            int strength = getRandomAttribute();
            int agility = getRandomAttribute();
            int endurance = getRandomAttribute();

            // Убрать ручной расчет здоровья - Character сам рассчитает
            Character character = new Character(characterName, characterClass,
                    strength,
                    agility,
                    endurance);

            applyInitialClassBonuses(character, characterClass);

            System.out.println();
            printCharacterInfo(character);
            return character;

        } catch (Exception e) {
            System.out.println("Ошибка создания персонажа: " + e.getMessage());
            throw new CharacterCreationException("Не удалось создать персонажа", e);
        }
    }

    public static int getRandomAttribute() {
        return random.nextInt(3) + 1;
    }

    private static void applyInitialClassBonuses(Character character, CharacterClass characterClass) {
        System.out.println("\nПрименяем бонусы начального класса...");

        // Специфичные бонусы класса 1 уровня
        characterClass.useClassBonusLevelOne(character);
        System.out.println();
    }

    public static String getCharacterName() {

        while (true) {
            System.out.print("\n\nПожалуйста, придумайте имя вашему игровому персонажу: ");
            String name = scanner.nextLine().trim();

            if (name == null || name.isEmpty()) {
                System.out.println("Имя не может быть пустым! Пожалуйста, введите имя.");
                continue;
            }

            if (name.length() < 2) {
                System.out.println("Имя должно содержать минимум 2 символа.");
                continue;
            }

            if (name.length() > 20) {
                System.out.println("Имя не должно превышать 20 символов.");
                continue;
            }

            System.out.print("Вы выбрали имя: \"" + name + "\". Это правильное имя? (да/нет): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("да") || confirmation.equals("д") || confirmation.equals("y") || confirmation.equals("yes")) {
                return name;
            } else {
                System.out.println("Хорошо, давайте попробуем другое имя.");
            }
        }
    }

    public static CharacterClass chooseCharacterClass() {
        System.out.println("\nВыберите основной класс персонажа: ");
        System.out.println("\n1. " + new Rogue().getClassName() + " - " + new Rogue().getDescription());
        System.out.println(new Rogue().getRogueClassInfo());
        System.out.println("\n2. " + new Warrior().getClassName() + " - " + new Warrior().getDescription());
        System.out.println(new Warrior().getWarriorClassInfo());
        System.out.println("\n3. " + new Barbarian().getClassName() + " - " + new Barbarian().getDescription());
        System.out.println(new Barbarian().getBarbarianClassInfo());
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

    public static void printCharacterInfo(Character character) {
        System.out.printf("""
                        +------------------------------------+
                        |       ИНФОРМАЦИЯ О ПЕРСОНАЖЕ       |
                        +------------------------------------+
                        | Имя: %-29s |
                        | Уровень персонажа: %-15d |
                        | Основной класс: %-18s |
                        | Уровень основного класса: %-8d |
                        +------------------------------------+
                        | Здоровье: %-24.1f |
                        | Сила: %-28d |
                        | Ловкость: %-24d |
                        | Выносливость: %-20d |
                        +------------------------------------+
                        | Стартовое оружие: %-16s |
                        | Урон оружия: %-21d |
                        | Бонус урона от силы: %-13d |
                        | Общий урон: %-22d |
                        +------------------------------------+
                        %n""",
                character.getName(),
                character.getCharacterLevel(),
                character.getMainClass().getClassName(),
                character.getClasses().getFirst().getLevel(),
                character.getHealth(),
                character.getStrength(),
                character.getAgility(),
                character.getEndurance(),
                character.getMainClass().getStartWeapon().getName(),
                character.getMainClass().getStartWeapon().getDamage(),
                character.getStrength(),
                character.getTotalDamage()
        );
    }
}