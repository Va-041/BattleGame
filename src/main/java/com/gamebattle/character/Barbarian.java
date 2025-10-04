package com.gamebattle.character;

import com.gamebattle.weapon.Club;

/**
 *  Класс для описания класса игрового персонажа "Варвар"
 */

public class Barbarian extends CharacterClass {
    public Barbarian() {
        super("Варвар", "Неистовый воин с яростью", new Club());
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
        return 6.0;
    }
}
