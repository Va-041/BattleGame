package com.gamebattle.weapon;

/**
 *  Класс легендарного меча
 */

public class LegendarySword extends Weapon {

    public LegendarySword(String name, int damage) {
        super("Легендарный меч", 10, "Рубящий");
    }

    @Override
    public void useWeapon() {
        System.out.println("Замахивается оружием " + name + " с уроном" + damage + " ед.");
    }
}
