package com.gamebattle.monsters;

/**
 *  Класс для описания монстра - Слайма
 */

import com.gamebattle.character.Character;
import com.gamebattle.weapon.Spear;
import com.gamebattle.weapon.Weapon;

public class Slime extends Monster {

    public Slime() {
        super("Слайм", 8, 3, 1, 2, 1,
                "Рубящее оружие не наносит ему урона (но урон от силы и прочих особенностей, \n" +
                        "даже порыва к действию воина, работает)",
                new Spear());
    }

    @Override
    public void useSpecialAbility(Character target, int turnCount) {
        // Пассивная способность - обрабатывается в applySlashingImmunity
    }

    @Override
    public boolean isSpecialAbilityAvailable(int turnCount) {
        return false;
    }

    // Метод для применения иммунитета к рубящему оружию
    public int applySlashingImmunity(Weapon playerWeapon, int weaponDamage, int bonusDamage) {
        if (playerWeapon.getDamageType().equals("Рубящий")) {
            System.out.println("💚 Слайм поглощает рубящий удар! Урон от оружия игнорируется.");
            System.out.println("Бонусный урон (" + bonusDamage + ") всё равно проходит!");
            return bonusDamage;
        }
        return weaponDamage + bonusDamage;
    }
}