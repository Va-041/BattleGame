package com.gamebattle.monsters;

import com.gamebattle.weapon.Spear;

public class Slime extends Monster {

    public Slime() {
        super("Слайм", 8, 3, 1, 2, 1,
                "Рубящее оружие не наносит ему урона (но урон от силы и прочих особенностей, \n" +
                        "даже порыва к действию воина, работает)",
                new Spear());
    }
}
