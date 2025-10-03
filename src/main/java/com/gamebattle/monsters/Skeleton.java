package com.gamebattle.monsters;

public class Skeleton extends Monster {

    public Skeleton(String name, double health, int strength, int agility, int endurance, int weaponDamage,
                    String features,  String rewardForWinning) {
        super("Скелет", 10, 2, 2, 1, 2,
                "Двойной урон от дробящего оружия", "Дубина");
    }
}
