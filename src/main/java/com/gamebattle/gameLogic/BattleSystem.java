package com.gamebattle.gameLogic;

/**
 *  Класс для описания работы механики битвы
 */

import com.gamebattle.character.Rogue;
import com.gamebattle.monsters.Monster;
import com.gamebattle.character.Character;

public class BattleSystem {

    // метод восстанавливающий хп до максимума при победе

    // метод предлагающий заменить оружие на новое после победы моба (как на более слабое, так и на более сильное,
    // смотря какой моб побежден (какое у него оружие то и выпадает)

    // метод возвращающий игрока к созданию персонажа при поражении

    // метод победы если было побеждено 5 монстров подряд (счётчик побежденных мобов)

    // метод работы с атрибутами:
    // Сила: значение силы прибавляется к урону от оружия.
    // Ловкость: повышает шанс попасть или уклониться от атаки. Подробнее - в разделе Бой.
    // Выносливость: значение выносливости прибавляется к здоровью при каждом повышении уровня.




    // метод восстанавливающий хп до максимума при победе
    public static void restoreHealth(Character player) {
        // нужно будет добавить поле maxHealth в Character
        System.out.println(player.getName() + " полностью восстанавливает здоровье!");
    }

    // метод предлагающий заменить оружие на новое после победы моба
    public static void offerWeaponDrop(Character player, Monster monster) {
        System.out.println("Монстр выбросил оружие! Хотите заменить?");
        // логика замены оружия
    }

    // метод возвращающий игрока к созданию персонажа при поражении
    public static void playerDefeated() {
        System.out.println("Персонаж повержен! Создайте нового персонажа.");
        // возврат к созданию персонажа
    }

    // метод победы если было побеждено 5 монстров подряд
    public static void checkVictory(int monstersDefeated) {
        if (monstersDefeated >= 5) {
            System.out.println("🎉 ПОБЕДА! Вы победили 5 монстров подряд!");
        }
    }

    public static void showBattleInfo(Character player, Monster monster) {
        double playerHitChance = calculateHitChance(player, monster);
        double playerEvasionChance = calculateEvasionChance(player, monster);
        double monsterHitChance = calculateMonsterHitChance(monster, player); // переименовал
        double monsterEvasionChance = calculateMonsterEvasionChance(monster, player); // переименовал

        System.out.printf("""
                ⚔️  ИНФОРМАЦИЯ О БОЕ:
                %s против %s
                
                %s:
                - Урон: %d
                - Шанс попадания: %.1f%%
                - Шанс уклонения: %.1f%%
                
                %s:
                - Урон: %d  
                - Шанс попадания: %.1f%%
                - Шанс уклонения: %.1f%%
                %n""",
                player.getName(), monster.getName(),
                player.getName(), player.getTotalDamage(), playerHitChance, playerEvasionChance,
                monster.getName(), monster.getWeaponDamage(), monsterHitChance, monsterEvasionChance
        );
    }

    private static double calculateHitChance(Character attacker, Monster target) {
        // Шанс попадания зависит от ловкости (cлучайное число от единицы до суммы (ловкости персонажа на момент боя + ловкость монстра)
        // если полученное число меньше или равно ловкости монстра - промах, иначе попадание.
        return 5.0;
    }

    private static double calculateEvasionChance(Character defender, Monster attacker) {
        // Шанс уклонения зависит от ловкости (и его нужно сделать в обратном варианте шанса попадания: т.е. когда
        // рассчитывается шанс попадания у монстра: cлучайное число от единицы до суммы (ловкости персонажа на момент боя + ловкость монстра)
        // если полученное число меньше нашей ловкости - мы уклоняемся. Если число больше нашей ловкости - мы получаем удар).
        return 2.0;
    }

    // метод для атаки игрока
    public static boolean playerAttack(Character player, Monster monster) {
        // Случайное число от 1 до (ловкость игрока + ловкость монстра)
        int randomRoll = (int) (Math.random() * (player.getAgility() + monster.getAgility())) + 1;

        if (randomRoll <= monster.getAgility()) {
            // Промах - число меньше или равно ловкости монстра
            System.out.println(player.getName() + " промахивается! (выпало: " + randomRoll + ", ловкость монстра: " + monster.getAgility() + ")");
            return false;
        } else {
            // Попадание
            int baseDamage = player.getTotalDamage();

            // Проверяем бонус скрытой атаки для разбойника
            if (player.getMainClass() instanceof Rogue) {
                Rogue rogueClass = (Rogue) player.getMainClass();
                int sneakAttackBonus = rogueClass.applySneakAttackBonus(player, monster);
                baseDamage += sneakAttackBonus;
            }

            System.out.println(player.getName() + " атакует " + monster.getName() + " и наносит " + baseDamage + " урона!");
            monster.takeDamage(baseDamage);
            return true;
        }
    }

    // методы для монстра
    private static double calculateMonsterHitChance(Monster attacker, Character target) {
        return 50.0 + (attacker.getAgility() * 5) - (target.getAgility() * 3);
    }

    private static double calculateMonsterEvasionChance(Monster defender, Character attacker) {
        return 20.0 + (defender.getAgility() * 4);
    }

    // метод для атаки монстра
    public static boolean monsterAttack(Monster monster, Character player) {
        // Случайное число от 1 до (ловкость монстра + ловкость игрока)
        int randomRoll = (int) (Math.random() * (monster.getAgility() + player.getAgility())) + 1;

        if (randomRoll <= player.getAgility()) {
            // Игрок уклонился - число меньше или равно ловкости игрока
            System.out.println(player.getName() + " уклоняется от атаки! (выпало: " + randomRoll + ", ловкость игрока: " + player.getAgility() + ")");
            return false;
        } else {
            // Попадание монстра
            System.out.println("Монстр " + monster.getName() + " атакует " + player.getName() + " и наносит " + monster.getWeaponDamage() + " урона!");
            player.takeDamage(monster.getWeaponDamage());
            return true;
        }
    }




}
