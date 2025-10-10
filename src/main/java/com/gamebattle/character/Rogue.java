package com.gamebattle.character;

/**
 *  Класс для описания класса игрового персонажа "Разбойник"
 */

import com.gamebattle.gameUtils.SleepTime;
import com.gamebattle.monsters.Monster;
import com.gamebattle.weapon.Dagger;

public class Rogue extends CharacterClass {

    private boolean hasSneakAttack = false;
    private boolean hasAgilityBonus = false;
    private boolean hasPoisonBonus = false;

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
    public double getHealthUpByClassLevel(Character target) {
        return 4.0;
    }

    @Override
    public void useClassBonusLevelOne(Character target) {
        this.hasSneakAttack = true;
        SleepTime.sleep(300);
        System.out.println("Разбойник получает бонус 'Скрытая атака': +1 к урону при преимуществе в ловкости");
        SleepTime.sleep(300);
    }

    @Override
    public void useClassBonusLevelTwo(Character target) {
        this.hasAgilityBonus = true;
        target.applyAgilityBonus(1);
        SleepTime.sleep(300);
        System.out.println("Разбойник получает бонус 'Проворство': +1 к ловкости");
        SleepTime.sleep(300);
    }

    @Override
    public void useClassBonusLevelThree(Character target) {
        this.hasPoisonBonus = true;
        SleepTime.sleep(300);
        System.out.println("Разбойник получает бонус 'Яд': Наносит дополнительные +1 урона на втором ходу, " +
                "+2 на третьем и так далее");
        SleepTime.sleep(300);
    }

    public void applyDamageBonuses(Character rogue, Monster target, int turnCount) {

        // Бонус скрытой атаки (1 уровень)
        if (hasSneakAttack && rogue.getAgility() > target.getAgility()) {
            SleepTime.sleep(300);
            System.out.println("⚡ Скрытая атака! Ловкость разбойника выше, +1 к урону");
            SleepTime.sleep(300);
            rogue.applyDamageBonus(1);
        }

        // Бонус яда (3 уровень)
        if (hasPoisonBonus && turnCount >= 2) {
            int poisonBonus = turnCount - 1;
            SleepTime.sleep(300);
            System.out.println("☠️ Яд! Дополнительный урон: +" + poisonBonus);
            SleepTime.sleep(300);
            rogue.applyDamageBonus(poisonBonus);
        }
    }

    public boolean hasSneakAttack() {
        return hasSneakAttack;
    }
    public boolean hasAgilityBonus() {
        return hasAgilityBonus;
    }
    public boolean hasPoisonBonus() {
        return hasPoisonBonus;
    }
}