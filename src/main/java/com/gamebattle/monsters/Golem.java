package com.gamebattle.monsters;

/**
 *  Класс для описания монстра - Голема
 */

import com.gamebattle.character.Character;
import com.gamebattle.weapon.Axe;

public class Golem extends Monster {

    public Golem() {
        super("Голем", 10, 3, 1, 3, 1,
                "Каменная кожа: получаемый урон снижается на значение выносливости",
                new Axe());
    }

    @Override
    public void useSpecialAbility(Character target, int turnCount) {
        // Пассивная способность - обрабатывается в applyStoneSkin
    }

    @Override
    public boolean isSpecialAbilityAvailable(int turnCount) {
        return false;
    }

    // Метод для применения каменной кожи
    public int applyStoneSkin(int incomingDamage) {

        // Снижение урона равно выносливости голема
        int damageReduction = this.getEndurance();
        int reducedDamage = Math.max(0, incomingDamage - damageReduction);

        if (damageReduction > 0 && reducedDamage < incomingDamage) {
            System.out.println("🗿 Каменная кожа Голема поглощает " + damageReduction + " урона!");
            System.out.println("Полученный урон: " + incomingDamage + " -> " + reducedDamage);
        }

        return reducedDamage;
    }
}