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
import com.gamebattle.gameUtils.SleepTime;

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

    // метод для получения рандомного значения начальных атрибутов
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
            System.out.print("\nПожалуйста, придумайте имя вашему персонажу: ");
            String name = scanner.nextLine().trim();

            if (name == null || name.isEmpty()) {
                System.out.println("Имя не может быть пустым! Пожалуйста, введите имя.");
                continue;
            }

            if (name.length() < 2) {
                System.out.println("Имя должно содержать минимум 2 символа.");
                continue;
            }

            if (name.length() > 13) {
                System.out.println("Имя не должно превышать 13 символов.");
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
        SleepTime.sleepSeconds(1);
        System.out.println("\nВыберите основной класс персонажа: ");

        SleepTime.sleepSeconds(1);
        System.out.println("\n1. " + new Rogue().getClassName() + " - " + new Rogue().getDescription());
        System.out.println(new Rogue().getRogueClassInfo());

        SleepTime.sleepSeconds(1);
        System.out.println("\n2. " + new Warrior().getClassName() + " - " + new Warrior().getDescription());
        System.out.println(new Warrior().getWarriorClassInfo());

        SleepTime.sleepSeconds(1);
        System.out.println("\n3. " + new Barbarian().getClassName() + " - " + new Barbarian().getDescription());
        System.out.println(new Barbarian().getBarbarianClassInfo());

        SleepTime.sleep(300);
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
        String[] lines = {
                "+------------------------------------+",
                "|       ИНФОРМАЦИЯ О ПЕРСОНАЖЕ       |",
                "+------------------------------------+",
                String.format("| Имя: %-29s |", character.getName()),
                String.format("| Уровень персонажа: %-15d |", character.getCharacterLevel()),
                String.format("| Основной класс: %-18s |", character.getMainClass().getClassName()),
                String.format("| Уровень основного класса: %-8d |", character.getClasses().getFirst().getLevel()),
                "+------------------------------------+",
                String.format("| Здоровье: %-24.1f |", character.getHealth()),
                String.format("| Сила: %-28d |", character.getStrength()),
                String.format("| Ловкость: %-24d |", character.getAgility()),
                String.format("| Выносливость: %-20d |", character.getEndurance()),
                "+------------------------------------+",
                String.format("| Стартовое оружие: %-16s |", character.getMainClass().getStartWeapon().getName()),
                String.format("| Урон оружия: %-21d |", character.getMainClass().getStartWeapon().getDamage()),
                String.format("| Бонус урона от силы: %-13d |", character.getStrength()),
                String.format("| Общий урон: %-22d |", character.getTotalDamage()),
                "+------------------------------------+"
        };

        for (String line : lines) {
            System.out.println(line);
            SleepTime.sleep(100);
        }
        System.out.println("\n");
    }
}