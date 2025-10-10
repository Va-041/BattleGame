package com.gamebattle.character;

/**
 *  Класс для описания класса игрового персонажа "Варвар"
 */

import com.gamebattle.gameUtils.SleepTime;
import com.gamebattle.monsters.Monster;
import com.gamebattle.weapon.Club;

public class Barbarian extends CharacterClass {

    private boolean hasRageBonus = false;
    private boolean hasRockSkinBonus = false;
    private boolean hasEnduranceBonus = false;

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
    public double getHealthUpByClassLevel(Character target) {
        return 6.0;
    }

    @Override
    public void useClassBonusLevelOne(Character target) {
        this.hasRageBonus = true;
        SleepTime.sleep(300);
        System.out.println("Варвар получает бонус 'Ярость': +2 к урону в первые 3 хода, далее -1 к урону в ход");
        SleepTime.sleep(300);
    }

    @Override
    public void useClassBonusLevelTwo(Character target) {
        this.hasRockSkinBonus = true;
        SleepTime.sleep(300);
        System.out.println("Варвар получает бонус 'Каменная кожа': получаемый урон снижается на значение выносливости");
        SleepTime.sleep(300);
    }

    @Override
    public void useClassBonusLevelThree(Character target) {
        this.hasEnduranceBonus = true;
        target.applyEnduranceBonus(1);
        SleepTime.sleep(300);
        System.out.println("Варвар получает бонус: +1 к выносливости");
        SleepTime.sleep(300);
    }

    // Бонус "Ярость" (1 уровень) - +2 урона в первые 3 хода, потом -1
    public void applyDamageBonuses(Character barbarian, Monster target, int turnCount) {
        if (hasRageBonus) {
            if (turnCount <= 3) {
                SleepTime.sleep(300);
                System.out.println("⚡ Активируется 'Ярость'! +2 к урону на ходу " + turnCount);
                SleepTime.sleep(300);
                barbarian.applyDamageBonus(2);
            } else if (turnCount == 4) {
                SleepTime.sleep(300);
                System.out.println("💢 Ярость ослабевает! -1 к урону начиная с этого хода");
                SleepTime.sleep(300);
                barbarian.applyDamageBonus(-1);
            } else {
                barbarian.applyDamageBonus(-1);
            }
        }
    }

    // Бонус "Каменная кожа" - уменьшение урона на значение выносливости
    public int applyRockSkinBonus(Character barbarian, Monster attacker, int incomingDamage) {
        if (hasRockSkinBonus) {
            int endurance = barbarian.getEndurance();
            int reducedDamage = Math.max(0, incomingDamage - endurance);
            SleepTime.sleep(300);
            System.out.println("🪨 Активируется 'Каменная кожа'! Урон уменьшен на " + endurance +
                    ": " + incomingDamage + " -> " + reducedDamage);
            SleepTime.sleep(300);

            return reducedDamage;
        }
        return incomingDamage;
    }

    // Геттеры для проверки бонусов
    public boolean hasRageBonus() { return hasRageBonus; }
    public boolean hasRockSkinBonus() { return hasRockSkinBonus; }
    public boolean hasEnduranceBonus() { return hasEnduranceBonus; }
}
