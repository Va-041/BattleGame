package com.gamebattle.character;

/**
 *  Класс для описания класса игрового персонажа "Воин"
 */

import com.gamebattle.weapon.Sword;

public class Warrior extends CharacterClass {
    public Warrior() {
        super("Воин", "Сильный и выносливый боец", new Sword());
    }

    public String getWarriorClassInfo() {
        return
                """
                    +------------+---------------------+------------------+--------------------------+------------------------+---------------------------+
                    | Имя класса | Здоровье за уровень | Начальное оружие |    Бонус за 1 уровень    |   Бонус за 2 уровень   |    Бонус за 3 уровень     |
                    +------------+---------------------+------------------+--------------------------+------------------------+---------------------------+
                    |            |                     | Меч (3 урона)    | Порыв к действию:        | Щит:                   |                           |
                    |    Воин    |      5 единиц       |                  | В первый ход наносит     | -3 к получаемому урону |          Сила +1          |
                    |            |                     | Тип урона:       | двойной урон оружием по  | если сила персонажа    |                           |
                    |            |                     | Рубящий          | цели                     | выше силы атакующего   |                           |
                    +------------+---------------------+------------------+--------------------------+------------------------+---------------------------+
                    """;
    }

    @Override
    public double getBaseHealth() {
        return 5.0;
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
    public double getHealthUpByClassLevel(Character target) {
        return 5.0;
    }
}
