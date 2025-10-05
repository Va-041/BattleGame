package com.gamebattle.monsters;

import com.gamebattle.weapon.Dagger;

public class Goblin extends Monster {

    public Goblin() {
        super("Гоблин", 5, 1, 1, 1,
                new Dagger(), // оружие, которое использует
                "Без особенностей",
                new Dagger()); // награда за победу
    }
}
