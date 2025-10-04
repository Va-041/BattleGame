package com.gamebattle.gameLogic;

import com.gamebattle.character.Barbarian;
import com.gamebattle.character.Character;
import com.gamebattle.character.CharacterClass;
import com.gamebattle.character.Rogue;
import com.gamebattle.character.Warrior;
import java.util.Scanner;

public class CreateCharacter {

    public static Character createNewCharacter() {
        String characterName = getCharacterName();
        CharacterClass characterClass = chooseCharacterClass();

        Character character = new Character(characterName, characterClass, getCharacterLevel(), getCharacterHealth(),
                getCharacterStrength(), getCharacterAgility(), getCharacterEndurance());

        printCharacterInfo(character);

        return character;
    }

    public static String getCharacterName() {
        System.out.print("\n\nПожалуйста, придумайте имя вашему игровому персонажу: ");
        return getScannerInput().nextLine();
    }

    public static CharacterClass chooseCharacterClass() {
        System.out.println("\nВыберите класс персонажа:");
        System.out.println("1. " + new Warrior().getClassName() + " - " + new Warrior().getDescription());
        System.out.println("2. " + new Barbarian().getClassName() + " - " + new Barbarian().getDescription());
        System.out.println("3. " + new Rogue().getClassName() + " - " + new Rogue().getDescription());
        System.out.print("\nВаш выбор (1-3): ");

        while (true) {
            String input = getScannerInput().nextLine();
            switch (input) {
                case "1":
                    return new Warrior();
                case "2":
                    return new Barbarian();
                case "3":
                    return new Rogue();
                default:
                    System.out.print("Неверный выбор. Пожалуйста, введите 1, 2 или 3: ");
            }
        }
    }

    public static int getCharacterLevel() {
        return 1;
    }

    public static double getCharacterHealth() {
        return 10.0;
    }

    public static int getCharacterStrength() {
        return 1;
    }

    public static int getCharacterAgility() {
        return 1;
    }

    public static int getCharacterEndurance() {
        return 1;
    }

    public static void printCharacterInfo(Character character) {
        System.out.println("\n=== ИНФОРМАЦИЯ О ПЕРСОНАЖЕ ===");
        System.out.println("Имя персонажа: " + character.getName());
        System.out.println("Класс: " + character.getCharacterClass().getClassName());
        System.out.println("Уровень: " + character.getLevel());
        System.out.println("Здоровье: " + character.getHealth());
        System.out.println("Сила: " + character.getStrength());
        System.out.println("Ловкость: " + character.getAgility());
        System.out.println("Выносливость: " + character.getEndurance());
        System.out.println("Оружие: " + character.getCharacterClass().getStartWeapon().getName());
        System.out.println("==============================");
    }

    public static Scanner getScannerInput() {
        return new Scanner(System.in);
    }
}