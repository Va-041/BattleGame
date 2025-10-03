package com.gamebattle.character;

/**
 *  Класс для описания игрового персонажа
 */

public class Character {

    String name;
    String characterClass;
    int level;
    double health;
    int strength;
    int agility;
    int endurance;

    public Character(String name, String characterClass, int level, double health, int strength, int agility,
                     int endurance) {
        this.name = name;
        this.characterClass = characterClass;
        this.level = level;
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.endurance = endurance;
    }

    public void takeDamage(double damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.health = 0;
            System.out.println(name + " повержен!");
        }
    }

    public boolean isAlive() {
        return health > 0;
    }


    // Геттеры
    public String getName() {
        return this.name;
    }

    public String getCharacterClass() {
        return this.characterClass;
    }

    public int getLevel() {
        return this.level;
    }

    public double getHealth() {
        return this.health;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getAgility() {
        return this.agility;
    }

    public int getEndurance() {
        return this.endurance;
    }
}
