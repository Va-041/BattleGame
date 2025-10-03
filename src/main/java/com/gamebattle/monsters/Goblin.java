package com.gamebattle.monsters;

public class Goblin extends Monster {

    public Goblin(String name, double health, int strength, int agility, int endurance, int weaponDamage,
                  String features,  String rewardForWinning) {
        super ("Гоблин", 5, 1, 1, 1, 2,
                "Без особенностей", "Кинжал");
    }
}
