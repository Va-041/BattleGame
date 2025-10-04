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

    public Character(String name, CharacterClass startingClass,
                     double health, int strength, int agility, int endurance) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.endurance = endurance;
        addClass(startingClass, 1);
    }

    // Методы для изменения состояния
    public void increaseCharacterLevel() {
        this.characterLevel++;
    }

    public void addClass(CharacterClass characterClass, int level) {
        this.classes.add(new CharacterClassLevel(characterClass, level));
    }

    public void increaseClassLevel(CharacterClass characterClass) {
        CharacterClassLevel existingClass = findClass(characterClass);
        if (existingClass != null) {
            existingClass.increaseLevel();
        }
    }

    // методы для расчёта характеристик
    public int getTotalDamage() {
        // Урон = урон оружия + сила
        return getMainClass().getStartWeapon().getDamage() + this.strength;
    }

//    public double getHitChance() {
//        // Шанс попадания зависит от ловкости (cлучайное число от единицы до суммы (ловкости персонажа на момент боя + ловкость монстра)
//        // если полученное число меньше или равно ловкости монстра - промах, иначе попадание.
//        return 1.0;
//    }
//
//    public double getEvasionChance() {
//        // Шанс уклонения зависит от ловкости (и его нужно сделать в обратном варианте шанса попадания: т.е. когда
//        // рассчитывается шанс попадания у монстра: cлучайное число от единицы до суммы (ловкости персонажа на момент боя + ловкость монстра)
//        // если полученное число меньше нашей ловкости - мы уклоняемся. Если число больше нашей ловкости - мы получаем удар).
//        return 2.0;
//    }

    // Методы для применения бонусов
    public void applyHealthBonus(double bonus) {
        this.health += bonus;
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

    // Геттеры
    public String getName() { return this.name; }
    public int getCharacterLevel() { return this.characterLevel; }
    public List<CharacterClassLevel> getClasses() { return new ArrayList<>(this.classes); }
    public double getHealth() { return this.health; }
    public int getStrength() { return this.strength; }
    public int getAgility() { return this.agility; }
    public int getEndurance() { return this.endurance; }
    public CharacterClass getMainClass() {
        return classes.isEmpty() ? null : classes.getFirst().getCharacterClass();
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
}