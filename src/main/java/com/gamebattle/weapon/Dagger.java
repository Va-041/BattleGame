package com.gamebattle.weapon;

/**
 *  Класс кинжала
 */

public class Dagger extends Weapon {

    public Dagger(String name, int damage) {
        super("Кинжал", 2, "Колющий");
    }

    @Override
    public void useWeapon() {
        System.out.println("Наносит удар оружием " + name + " с уроном " + damage + " ед.");
    }
}
