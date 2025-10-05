package com.gamebattle.monsters;

import com.gamebattle.weapon.LegendarySword;

public class Dragon extends Monster {

    public Dragon() {
        super ("Дракон", 20, 3, 3, 3,
                new LegendarySword(),
                "Каждый 3-й ход дышит огнём, нанося дополнительно 3 урона",
                new LegendarySword());
    }
}
