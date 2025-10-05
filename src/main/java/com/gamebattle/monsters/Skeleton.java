package com.gamebattle.monsters;

import com.gamebattle.weapon.Club;
import com.gamebattle.weapon.Sword;

public class Skeleton extends Monster {

    public Skeleton() {
        super("Скелет", 10, 2, 2, 1,
                new Club(),
                "Двойной урон от дробящего оружия",
                new Club());
    }
}
