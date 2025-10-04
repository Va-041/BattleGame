package com.gamebattle.weapon;

/**
 *  Класс копья
 */

public class Spear extends Weapon {

    public Spear() {
        super("Копьё", 2,"Колющий");
    }

    @Override
    public void useWeapon() {
        System.out.println("Производит удар оружием " + name + " с уроном" + damage + " ед.");
    }
}
