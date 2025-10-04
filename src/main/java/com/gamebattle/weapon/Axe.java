package com.gamebattle.weapon;

/**
 *  Класс топора
 */

public class Axe extends Weapon {

    public Axe() {
        super("Топор", 4, "Рубящий");
    }

    @Override
    public void useWeapon() {
        System.out.println("Размахивается оружием " + name + " с уроном " + damage + " ед.");
    }
}
