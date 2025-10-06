package com.gamebattle.character;

/**
 *  Класс для описания игровых классов персонажа
 */

import com.gamebattle.weapon.Weapon;

public abstract class CharacterClass {
    protected String className;
    protected String description;
    protected Weapon startWeapon;

    public CharacterClass(String className, String description, Weapon startWeapon) {
        this.className = className;
        this.description = description;
        this.startWeapon = startWeapon;
    }

    public abstract double getBaseHealth();

    public abstract void useClassBonusLevelOne(Character target);
    public abstract void useClassBonusLevelTwo(Character target);
    public abstract void useClassBonusLevelThree(Character target);

    public abstract double getHealthUpByClassLevel(Character target);   // значение здоровья увеличивается при каждом
                                                                        // увеличении уровня игрового класса


    // Геттеры
    public String getClassName() { return this.className; }
    public String getDescription() { return this.description; }
    public Weapon getStartWeapon() { return this.startWeapon; }
}
