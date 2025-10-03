package com.gamebattle.monsters;

public class Slime extends Monster {

    public Slime(String name, double health, int strength, int agility, int endurance, int weaponDamage,
                 String features,  String rewardForWinning) {
        super("Слайм", 8, 3, 1, 2, 1,
                "Рубящее оружие не наносит ему урона (но урон от силы и прочих особенностей, даже порыва к " +
                        "действию воина, работает)", "Копьё");
    }
}
