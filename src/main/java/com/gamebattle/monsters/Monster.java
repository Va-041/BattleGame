package com.gamebattle.monsters;

import com.gamebattle.character.Character;

/**
 *  Класс для описания монстров
 */

public abstract class Monster {

    String name;
    double health;
    int strength;
    int agility;
    int endurance;
    int weaponDamage;
    String features;
    String rewardForWinning;

    public Monster(String name, double health, int strength, int agility, int endurance, int weaponDamage,
                   String features, String rewardForWinning) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.endurance = endurance;
        this.weaponDamage = weaponDamage;
        this.features = features;
        this.rewardForWinning = rewardForWinning;
    }

    public int getWeaponDamage() {
        return this.weaponDamage;
    }

    public void takeDamage(int weaponDamage) {
        this.health -= weaponDamage;

        if (this.health <= 0) {
            this.health = 0;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void attack(Character target) {
        System.out.println("Монстр " + name + " атакует " + target.getName() + " и наносит " + weaponDamage + " урона!");
        target.takeDamage(weaponDamage);
    }

    // Геттеры
    public String getName() {
        return this.name;
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
    public String getFeatures() {
        return this.features;
    }
    public String getRewardForWinning() {
        return this.rewardForWinning;
    }

}
