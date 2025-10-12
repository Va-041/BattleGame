package com.gamebattle.character;

/**
 * Класс для хранения информации о классе персонажа и его уровне
 */

public class CharacterClassLevel {
    private final CharacterClass characterClass;
    private int level;

    public CharacterClassLevel(CharacterClass characterClass, int level) {
        this.characterClass = characterClass;
        this.level = level;
    }

    public CharacterClass getCharacterClass() { return this.characterClass; }
    public int getLevel() { return this.level; }
    public void increaseLevel() { this.level++; }

    @Override
    public String toString() {
        return characterClass.getClassName() + " " + level;
    }
}
