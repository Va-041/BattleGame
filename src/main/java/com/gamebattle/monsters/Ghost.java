package com.gamebattle.monsters;

import com.gamebattle.weapon.Sword;

public class Ghost extends Monster {

    public Ghost() {
        super("Призрак", 6, 1, 3, 1,
                new Sword(),
                "Скрытая атака: +1 к урону если ловкость персонажа выше ловкости цели",
                new Sword());
    }
}
