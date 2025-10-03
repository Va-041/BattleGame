package com.gamebattle.monsters;

public class Ghost extends Monster {

    public Ghost(String name, double health, int strength, int agility, int endurance, int weaponDamage,
                 String features,  String rewardForWinning) {
        super("Призрак", 6, 1, 3, 1, 3,
                "Скрытая атака: +1 к урону если ловкость персонажа выше ловкости цели", "Меч");
    }
}
