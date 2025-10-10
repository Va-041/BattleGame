package com.gamebattle.character;

/**
 *  Класс для описания класса игрового персонажа "Воин"
 */

import com.gamebattle.gameUtils.SleepTime;
import com.gamebattle.monsters.Monster;
import com.gamebattle.weapon.Sword;

public class Warrior extends CharacterClass {

    private boolean hasRushToActionBonus = false;
    private boolean hasShieldBonus = false;
    private boolean hasStrengthBonus = false;

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
    public double getHealthUpByClassLevel(Character target) {
        return 5.0;
    }

    @Override
    public void useClassBonusLevelOne(Character target) {
        this.hasRushToActionBonus = true;
        SleepTime.sleep(300);
        System.out.println("Воин получает бонус 'Порыв к действию': двойной урон оружием  на первом ходу!");
        SleepTime.sleep(300);
    }

    @Override
    public void useClassBonusLevelTwo(Character target) {
        this.hasShieldBonus = true;
        SleepTime.sleep(300);
        System.out.println("Воин получает бонус 'Щит': -3 к полученному урону если сила персонажа выше силы атакующего!");
        SleepTime.sleep(300);
    }

    @Override
    public void useClassBonusLevelThree(Character target) {
        this.hasStrengthBonus = true;
        target.applyStrengthBonus(1);
        SleepTime.sleep(300);
        System.out.println("Воин получает бонус: +1 к силе");
        SleepTime.sleep(300);
    }

    // Бонус "Порыв к действию" (1 уровень) - двойной урон оружия на первом ходу
    public void applyDamageBonuses(Character warrior, Monster target, int turnCount) {
        if (hasRushToActionBonus && turnCount == 1) {
            int weaponDamage = warrior.getMainClass().getStartWeapon().getDamage();
            SleepTime.sleep(300);
            System.out.println("⚡ Активируется 'Порыв к действию'! Двойной урон от оружия (+" + weaponDamage + ")!");
            SleepTime.sleep(300);
            warrior.applyDamageBonus(weaponDamage);
        }
    }

    // Бонус "Щит" (2 уровень)
    public int applyShieldBonus(Character warrior, Monster attacker, int incomingDamage) {

        if (hasShieldBonus && warrior.getStrength() > attacker.getStrength()) {
            int reducedDamage = Math.max(0, incomingDamage - 3);
            SleepTime.sleep(300);
            System.out.println("🛡️ Активируется 'Щит'! Урон уменьшен на 3: " + incomingDamage + " -> " + reducedDamage);
            SleepTime.sleep(300);

            return reducedDamage;
        }
        return incomingDamage;
    }

    // Геттеры для проверки бонусов
    public boolean hasRushToActionBonus() { return hasRushToActionBonus; }
    public boolean hasShieldBonus() { return hasShieldBonus; }
    public boolean hasStrengthBonus() { return hasStrengthBonus; }
}
