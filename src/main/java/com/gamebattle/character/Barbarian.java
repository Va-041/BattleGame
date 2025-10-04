package com.gamebattle.character;

/**
 *  Класс для описания класса игрового персонажа "Варвар"
 */

import com.gamebattle.weapon.Club;

public class Barbarian extends CharacterClass {
    public Barbarian() {
        super("Варвар", "Неистовый воин с яростью", new Club());
    }

    public String getBarbarianClassInfo() {
        return
                """
                    +------------+---------------------+------------------+--------------------------+------------------------+---------------------------+
                    | Имя класса | Здоровье за уровень | Начальное оружие |    Бонус на 1 уровне     |   Бонус на 2 уровне    |    Бонус на 3 уровне      |
                    +------------+---------------------+------------------+--------------------------+------------------------+---------------------------+
                    |            |                     | Дубина (3 урона) | Ярость:                  | Каменная кожа:         |                           |
                    |   Варвар   |      6 единиц       |                  | +2 к урону в первые      | Получаемый урон        |      Выносливость +1      |
                    |            |                     | Тип урона:       | 3 хода, потом -1 к урону | снижается на значение  |                           |
                    |            |                     | Дробящий         |                          | выносливости           |                           |
                    +------------+---------------------+------------------+--------------------------+------------------------+---------------------------+
                    """;
    }

    @Override
    public double getBaseHealth() {
        return 6.0;
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
        return 6.0;
    }
}
