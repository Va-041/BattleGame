package com.gamebattle.monsters;

import com.gamebattle.character.Character;
import com.gamebattle.weapon.Sword;

public class Ghost extends Monster {

    public Ghost() {
        super("Призрак", 6, 1, 3, 1, 3,
                "Скрытая атака: +1 к урону если ловкость персонажа выше ловкости цели",
                new Sword());
    }

    @Override
    public void useSpecialAbility(Character target, int turnCount) {
        // Пассивная способность - обрабатывается в applySneakAttackBonus
    }

    @Override
    public boolean isSpecialAbilityAvailable(int turnCount) {
        // Скрытая атака доступна всегда при условии преимущества в ловкости
        return true;
    }

    // Метод для применения скрытой атаки
    public int applySneakAttackBonus(Character target) {
        if (this.getAgility() > target.getAgility()) {
            System.out.println("👻 Призрак использует скрытую атаку! +1 к урону");
            return 1; // Бонус к урону
        }
        return 0;
    }
}