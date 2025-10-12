package com.gamebattle.monsters;

/**
 *  Класс для описания монстра - Скелета
 */

import com.gamebattle.character.Character;
import com.gamebattle.weapon.Club;
import com.gamebattle.weapon.Weapon;

public class Skeleton extends Monster {

    public Skeleton() {
        super("Скелет", 10, 2, 2, 1, 2,
                "Двойной урон от дробящего оружия",
                new Club());
    }

    @Override
    public void useSpecialAbility(Character target, int turnCount) {
        // Пассивная способность - обрабатывается в applyBluntWeaponVulnerability
    }

    @Override
    public boolean isSpecialAbilityAvailable(int turnCount) {
        return false;
    }

    // Метод для проверки уязвимости к дробящему оружию
    public int applyBluntWeaponVulnerability(Weapon playerWeapon, int baseDamage) {
        if (playerWeapon.getDamageType().equals("Дробящий")) {
            System.out.println("💀 Скелет уязвим к дробящему оружию! Урон удваивается!");
            // удваиваем общий урон
            return baseDamage;
        }
        return 0;
    }
}
