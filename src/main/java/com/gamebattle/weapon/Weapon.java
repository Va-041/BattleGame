package com.gamebattle.weapon;

/**
 *  Общий класс для описания оружия
 */

public class Weapon {

    String name;
    int damage;
    String damageType;

    public Weapon(String name, int damage, String damageType) {
        this.name = name;
        this.damage = damage;
        this.damageType = damageType;
    }

    public void useWeapon() {
        System.out.println("Использует оружие " + name + " с уроном " + damage + " ед.");
    }

    // Геттеры
    public String getName() {
        return this.name;
    }

    public int getDamage() {
        return this.damage;
    }

    public String getDamageType() {
        return this.damageType;
    }
}
