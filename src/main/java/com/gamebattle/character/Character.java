package com.gamebattle.character;

/**
 * Класс для описания игрового персонажа
 */

import java.util.ArrayList;
import java.util.List;

public class Character {
    private String name = "Unnamed";
    private List<CharacterClassLevel> classes = new ArrayList<>();
    private int characterLevel = 0;
    private double health = 0.0;
    private int strength = 0;
    private int agility = 0;
    private int endurance = 0;
    private int damageBonus = 0;

    public Character(String name, CharacterClass startingClass,
                     int strength, int agility, int endurance) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.endurance = endurance;
        addClass(startingClass, 1);

        this.health = getMaxHealth();
    }

    // Методы для изменения состояния
    public void increaseCharacterLevel() {
        this.characterLevel++;
    }

    public void addClass(CharacterClass characterClass, int level) {
        System.out.println("Отладка addClass: Добавляем класс " + characterClass.getClassName() + " уровень " + level);
        this.classes.add(new CharacterClassLevel(characterClass, level));
        System.out.println("Отладка addClass: Теперь классы: " + this.classes);
    }

    public void increaseClassLevel(CharacterClass characterClass) {
        CharacterClassLevel existingClass = findClass(characterClass);
        if (existingClass != null) {
            existingClass.increaseLevel();
        }
    }

    // Базовый урон (постоянный)
    public int getBaseDamage() {
        return getMainClass().getStartWeapon().getDamage() + this.strength;
    }

    // Общий урон (базовый + временные бонусы)
    public int getTotalDamage() {
        return getBaseDamage() + this.damageBonus;
    }

    // Методы для применения бонусов классов
    public void applyHealthBonus(double bonus) {
        this.health += bonus;
    }

    public void applyDamageBonus(int bonus) {
        this.damageBonus += bonus;
    }

    public void resetDamageBonus() {
        this.damageBonus = 0;
    }

    public void applyStrengthBonus(int bonus) {
        this.strength += bonus;
    }

    public void applyAgilityBonus(int bonus) {
        this.agility += bonus;
    }

    public void applyEnduranceBonus(int bonus) {
        this.endurance += bonus;
    }


    public CharacterClassLevel findClass(CharacterClass characterClass) {
        for (CharacterClassLevel classLevel : classes) {
            if (classLevel.getCharacterClass().getClass() == characterClass.getClass()) {
                return classLevel;
            }
        }
        return null;
    }


    public boolean hasClass(CharacterClass characterClass) {
        return findClass(characterClass) != null;
    }


    public void takeDamage(double damage) {
        this.health -= damage;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void restoreHealth() {
        this.health = getMaxHealth();
    }

    public double getMaxHealth() {
        double maxHealth = 0;
        // здоровье от всех классов
        for (CharacterClassLevel classLevel : classes) {
            maxHealth += classLevel.getCharacterClass().getBaseHealth();
            // Также добавляем здоровье за уровни классов (кроме первого)
            if (classLevel.getLevel() > 1) {
                maxHealth += classLevel.getCharacterClass().getHealthUpByClassLevel(this) * (classLevel.getLevel() - 1);
            }
        }
        // Добавляем здоровье от выносливости
        maxHealth += this.endurance;
        return maxHealth;
    }



    // Геттеры
    public String getName() { return this.name; }
    public int getCharacterLevel() { return this.characterLevel; }
    public List<CharacterClassLevel> getClasses() { return new ArrayList<>(this.classes); }
    public double getHealth() { return this.health; }
    public int getStrength() { return this.strength; }
    public int getAgility() { return this.agility; }
    public int getEndurance() { return this.endurance; }
    public int getDamageBonus() { return this.damageBonus; }
    public CharacterClass getMainClass() {
        return classes.isEmpty() ? null : classes.getFirst().getCharacterClass();
    }
}