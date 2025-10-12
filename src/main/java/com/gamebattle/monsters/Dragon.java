package com.gamebattle.monsters;

/**
 *  Класс для описания монстра - Дракона
 */

import com.gamebattle.character.Character;
import com.gamebattle.weapon.LegendarySword;

public class Dragon extends Monster {


    public Dragon() {
        super("Дракон", 20, 3, 3, 3, 4,
                "Каждый 3-й ход дышит огнём, нанося дополнительно 3 урона",
                new LegendarySword());
    }

    @Override
    public void useSpecialAbility(Character target, int turnCount) {
        // Пассивная способность - обрабатывается в applyFireBreathBonus
    }

    @Override
    public boolean isSpecialAbilityAvailable(int turnCount) {
        // каждый 3-й ход
        return turnCount % 3 == 0;
    }

    // Метод для получения бонуса от огненного дыхания
    public int applyFireBreathBonus(int turnCount) {
        if (isSpecialAbilityAvailable(turnCount)) {
            int fireBreathBonus = 3;

            System.out.println("🐉 Дракон извергает поток огня! +" + fireBreathBonus + " к урону!");
            return fireBreathBonus;
        }
        return 0;
    }
}