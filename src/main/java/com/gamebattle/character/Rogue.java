package com.gamebattle.character;

/**
 *  Класс для описания класса игрового персонажа "Разбойник"
 */

import com.gamebattle.monsters.Monster;
import com.gamebattle.weapon.Dagger;

public class Rogue extends CharacterClass {

    private boolean hasSneakAttack = false;
    private boolean hasAgilityBonus = false;

    public Rogue() {
        super("Разбойник", "Проворный и хитрый боец", new Dagger());
    }

    public String getRogueClassInfo() {
        return
                """
                    +------------+---------------------+------------------+--------------------------+------------------------+---------------------------+
                    | Имя класса | Здоровье за уровень | Начальное оружие |    Бонус за 1 уровень    |   Бонус за 2 уровень   |    Бонус за 3 уровень     |
                    +------------+---------------------+------------------+--------------------------+------------------------+---------------------------+
                    |            |                     | Кинжал (2 урона) | Скрытая атака:           |                        | Яд:                       |
                    | Разбойник  |      4 единицы      |                  | +1 к урону если ловкость |      Ловкость +1       | Наносит дополнительные +1 |
                    |            |                     | Тип урона:       | персонажа выше ловкости  |                        | урона на втором ходу,     |
                    |            |                     | Колющий          | монстра                  |                        | +2 на третьем и так далее |
                    +------------+---------------------+------------------+--------------------------+------------------------+---------------------------+
                    """;
    }

    @Override
    public double getBaseHealth() {
        return 4.0;
    }

    @Override
    public void useClassBonusLevelOne(Character target) {
        this.hasSneakAttack = true;
        System.out.println("Разбойник получает бонус 'Скрытая атака': +1 к урону при преимуществе в ловкости");
    }

    @Override
    public void useClassBonusLevelTwo(Character target) {
        this.hasAgilityBonus = true;
        target.applyAgilityBonus(1);
        System.out.println("Разбойник получает бонус 'Проворство': +1 к ловкости");
    }

    @Override
    public void useClassBonusLevelThree(Character target) {
        System.out.println("Разбойник получает бонус 'Яд': Наносит дополнительные +1 урона на втором ходу, " +
                "+2 на третьем и так далее");
    }

    @Override
    public double getHealthUpByClassLevel(Character target) {
        return 4.0;
    }

    public int applySneakAttackBonus(Character rogue, Monster target) {
        if (hasSneakAttack && rogue.getAgility() > target.getAgility()) {
            System.out.println("⚡ Скрытая атака! Ловкость разбойника выше, +1 к урону");
            return 1; // дополнительный урон
        }
        return 0; // без бонуса
    }

    // Геттер для проверки наличия способности
    public boolean hasSneakAttack() {
        return hasSneakAttack;
    }
}