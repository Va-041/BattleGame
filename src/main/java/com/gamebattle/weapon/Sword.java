package com.gamebattle.weapon;

/**
 *  Класс меча
 */

public class Sword extends Weapon{

    public Sword() {
        super("Меч", 3, "Рубящий");
    }

    @Override
    public void useWeapon() {
        System.out.println("Замахивается оружием " + name + " с уроном " + damage + " ед.");
    }
}
