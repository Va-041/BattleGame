package com.gamebattle.monsters;

public class Dragon extends Monster {

    public Dragon(String name, double health, int strength, int agility, int endurance, int weaponDamage,
                  String features,  String rewardForWinning) {
        super ("Дракон", 20, 3, 3, 3, 4, "Каждый 3-й ход " +
                "дышит огнём, нанося дополнительно 3 урона", "Легендарный меч");
    }
}
