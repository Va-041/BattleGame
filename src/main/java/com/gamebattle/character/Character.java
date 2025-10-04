package com.gamebattle.character;

/**
 *  Класс для описания игрового персонажа
 */

public class Character {
    private String name = "Unnamed";
    private final CharacterClass characterClass;
    private int level = 1;
    private double health = 0.0;
    private int strength = 0;
    private int agility = 0;
    private int endurance = 0;

    public Character(String name, CharacterClass characterClass, int level,
                     double health, int strength, int agility, int endurance) {
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

    public void levelUp() {
        this.level++;

        double healthBonus = this.characterClass.getHealthUpByLevel(this);
        this.health += healthBonus;

        System.out.println(this.name + " повысил уровень до " + this.level + "! +" + healthBonus + " к здоровью");

        // Применяем бонусы класса в зависимости от уровня
        if (this.level == 1) {
            this.characterClass.useClassBonusLevelOne(this);
        } else if (this.level == 2) {
            this.characterClass.useClassBonusLevelTwo(this);
        } else if (this.level == 3) {
            this.characterClass.useClassBonusLevelThree(this);
        }
    }


    // Геттеры
    public String getName() {
        return this.name;
    }

    public CharacterClass getCharacterClass() {
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
