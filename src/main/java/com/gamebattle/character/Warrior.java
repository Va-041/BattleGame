package com.gamebattle.character;

import com.gamebattle.weapon.Sword;

/**
 *  Класс для описания класса игрового персонажа "Воин"
 */

public class Warrior extends CharacterClass {
    public Warrior() {
        super("Воин", "Сильный и выносливый боец", new Sword());
    }

    @Override
    public void useClassBonusLevelOne(Character target) {
        // реализация для 1 уровня
    }

    @Override
    public void useClassBonusLevelTwo(Character target) {
        // реализация для 2 уровня
    }

    @Override
    public void useClassBonusLevelThree(Character target) {
        // реализация для 3 уровня
    }

    @Override
    public double getHealthUpByLevel (Character target) {
        return 5.0;
    }
}
