package com.gamebattle.weapon;

/**
 *  Класс дубины
 */

public class Club extends Weapon{

    public Club() {
        super("Дубина", 3, "Дробящий");
    }

    @Override
    public void useWeapon() {
        System.out.println("Размахивается оружием " + name + " с уроном" + damage + " ед.");
    }
}
