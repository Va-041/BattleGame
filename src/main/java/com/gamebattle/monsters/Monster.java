package com.gamebattle.monsters;

import com.gamebattle.character.Character;
import com.gamebattle.weapon.Weapon;

/**
 *  Класс для описания монстров
 */

public abstract class Monster {

    String name;
    double health;
    int strength;
    int agility;
    int endurance;
    int damage;
    String features;
    Weapon rewardForWinning;

    public Monster(String name, double health, int strength, int agility, int endurance, int damage,
                   String features, Weapon rewardForWinning) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.endurance = endurance;
        this.damage = damage;
        this.features = features;
        this.rewardForWinning = rewardForWinning;
    }

    public String getMonsterParameters() {
        String monsterParameters =
                "------------------------" + "\n" +
                "Здоровье:\t\t\t" + this.health + "\n" +
                "Сила:\t\t\t\t" + this.strength + "\n" +
                "Ловкость:\t\t\t" + this.agility + "\n" +
                "Выносливость:\t\t" + this.endurance + "\n" +
                "Урон:\t\t\t\t" +  this.damage + "\n"+
                "------------------------" + "\n" +
                "Особенности:\n" + this.features + "\n" +
                "------------------------" + "\n" +
                "Награда за убийство: " + this.rewardForWinning.getName() + "\n";

        return monsterParameters;
    }

    public int getDamage() {
        return this.damage;
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
        System.out.println("Монстр " + name + " атакует " + target.getName() + " и наносит " + getDamage() + " урона!");
        target.takeDamage(getDamage());
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
    public Weapon getRewardForWinning() {
        return this.rewardForWinning;
    }

}
