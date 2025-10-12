package com.gamebattle.monsters;

/**
 *  Общий класс для описания монстров
 */

import com.gamebattle.character.Character;
import com.gamebattle.gameUtils.SleepTime;
import com.gamebattle.weapon.Weapon;

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
        String[] parameters = {
                "------------------------",
                "Здоровье:\t\t\t" + this.health,
                "Сила:\t\t\t\t" + this.strength,
                "Ловкость:\t\t\t" + this.agility,
                "Выносливость:\t\t" + this.endurance,
                "Урон:\t\t\t\t" + this.damage,
                "------------------------",
                "Особенности:",
                this.features,
                "------------------------",
                "Награда за убийство: " + this.rewardForWinning.getName(),
                "------------------------"
        };

        return String.join("\n", parameters);
    }

    public void printMonsterParametersWithDelay() {
        String[] parameters = {
                "------------------------",
                "Здоровье:\t\t\t" + this.health,
                "Сила:\t\t\t\t" + this.strength,
                "Ловкость:\t\t\t" + this.agility,
                "Выносливость:\t\t" + this.endurance,
                "Урон:\t\t\t\t" + this.damage,
                "------------------------",
                "Особенности:",
                this.features,
                "------------------------",
                "Награда за убийство: " + this.rewardForWinning.getName(),
                "------------------------"
        };

        for (String line : parameters) {
            System.out.println(line);
            SleepTime.sleep(300);
        }
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

    public abstract void useSpecialAbility(Character target, int turnCount);


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

    public abstract boolean isSpecialAbilityAvailable(int turnCount);

}
