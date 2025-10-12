package com.gamebattle.monsters;

import com.gamebattle.character.Character;
import com.gamebattle.weapon.Dagger;

public class Goblin extends Monster {

    public Goblin() {
        super("Гоблин", 5, 1, 1, 1, 2,
                "Без особенностей",
                new Dagger()); // награда за победу
    }

    @Override
    public void useSpecialAbility(Character target, int turnCount) {
        // у гоблина нету особенностей
    }

    @Override
    public boolean isSpecialAbilityAvailable(int turnCount) {
        return false;
    }
}
