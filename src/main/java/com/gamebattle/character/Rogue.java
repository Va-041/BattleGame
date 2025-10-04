package com.gamebattle.character;

import com.gamebattle.weapon.Dagger;

/**
 *  Класс для описания класса игрового персонажа "Разбойник"
 */

public class Rogue extends CharacterClass {
    public Rogue() {
        super("Разбойник", "Проворный и хитрый боец", new Dagger());
    }

    @Override
    public void useClassBonusLevelOne(Character target) {
        System.out.println("Разбойник использует скрытую атаку!");
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
        return 4.0;
    }
}