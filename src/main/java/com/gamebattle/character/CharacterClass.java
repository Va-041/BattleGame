package com.gamebattle.character;

import com.gamebattle.weapon.Weapon;

/**
 *  Класс для описания игровых классов персонажа
 */


public abstract class CharacterClass {
    protected String className;
    protected String description;
    protected Weapon startWeapon;

    public CharacterClass(String className, String description, Weapon startWeapon) {
        this.className = className;
        this.description = description;
        this.startWeapon = startWeapon;
    }


    public abstract void useClassBonusLevelOne(Character target);

    public abstract void useClassBonusLevelTwo(Character target);

    public abstract void useClassBonusLevelThree(Character target);

    public abstract double getHealthUpByLevel(Character target);

    // Геттеры
    public String getClassName() { return this.className; }
    public String getDescription() { return this.description; }
    public Weapon getStartWeapon() { return this.startWeapon; }
}
