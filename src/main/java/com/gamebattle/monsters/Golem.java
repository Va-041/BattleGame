package com.gamebattle.monsters;

public class Golem extends Monster {

    public Golem(String name, double health, int strength, int agility, int endurance, int weaponDamage,
                 String features,  String rewardForWinning) {
        super("Голем", 10, 3, 1, 3, 1, "Каменная кожа: " +
                "получаемый урон снижается на значение выносливости", "Топор");
    }
}
