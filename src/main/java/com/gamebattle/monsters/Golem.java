package com.gamebattle.monsters;

import com.gamebattle.weapon.Axe;

public class Golem extends Monster {

    public Golem() {
        super("Голем", 10, 3, 1, 3,
                new Axe(),
                "Каменная кожа: получаемый урон снижается на значение выносливости",
                new Axe());
    }
}
