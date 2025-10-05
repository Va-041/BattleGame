package com.gamebattle.gameLogic;

/**
 *  Класс для описания запуска и проведения игры
 */

import com.gamebattle.Location.LocationAndLore;
import com.gamebattle.character.Character;
import com.gamebattle.character.CharacterClassLevel;
import com.gamebattle.character.LevelManager;
import com.gamebattle.customExceptions.CharacterCreationException;
import java.util.List;
import java.util.Scanner;

import static com.gamebattle.gameLogic.BattleSystem.monstersDefeated;

public class StartGame {

    private static Character player;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws CharacterCreationException {
        welcomeMessage();
        getGameInformation();
        getCharacter();

        startGameLoop();
    }

    public static void welcomeMessage() {
        System.out.println("\nДобро пожаловать в Epic Battle Game");
    }

    public static void getGameInformation() {
        System.out.println("\nПравила игры: \n");
        System.out.println(
                """
                    1. В начале игрок выбирает класс персонажа (воин, варвар или разбойник).
                       У персонажа есть 3 атрибута:
                       • Сила: значение силы прибавляется к урону от оружия.
                       • Ловкость: повышает шанс попасть или уклониться от атаки.
                       • Выносливость: значение выносливости прибавляется к здоровью при каждом повышении уровня.
                       У нового персонажа они равны случайному числу от 1 до 3 включительно.
                    
                    2. Персонаж сражается со случайным монстром:
                       Бой идёт по ходам, персонаж и монстр атакуют друг друга по очереди. Первым ходит тот, у кого выше
                       ловкость. Если она одинаковая, то первым ходит персонаж.
                       • При атаке сначала вычисляется шанс попадания: берётся случайное число от единицы до суммы
                         ловкости атакующего и цели. Если это число меньше или равно ловкости цели - атака промахнулась.
                       • Если атака попала, то считается изначальный урон: это урон оружия атакующего + его сила.
                       • Применяются все эффекты на атаки у атакующего (Порыв к действию, Ярость и другие).
                       • Применяются все эффекты на урон цели (Каменная кожа, Щит и другие).
                       • Если итоговый урон больше 0 - его значение вычитается из здоровья цели.
                       • Если здоровье цели опустилось до 0 или ниже - бой заканчивается победой атакующего.
                       • Если цель выжила, то теперь её очередь атаковать. Переходим к пункту 1.
                       • После каждого боя здоровье персонажа восстанавливается до максимума. Затем игроку предлагают
                         повысить уровень персонажа.
                       • Как и в "серьёзных" RPG, у нас есть возможность мультикласса:
                         при повышении уровня игрок может выбрать не только тот класс, с которым он начал игру, но и любой
                         другой. Тогда он получит +1 уровень в этом классе, и все бонусы от этого уровня.
                       • Максимальный суммарный уровень персонажа - 3. Когда он достигнут, игроку больше не предлагают
                         повысить уровень, после боя только восстанавливается здоровье.
                    
                    4. Если персонаж проиграл - игроку предлагается создать нового персонажа.
                    5. Если персонаж победил 5 монстров подряд - игра пройдена.
                    """
        );
    }

    public static void getCharacter() throws CharacterCreationException {
        player = CreateCharacter.createNewCharacter();
    }

    public static void startGameLoop() {
        // Начинаем с первой локации
        LocationAndLore.Location currentLocation = LocationNavigation.getStartLocation();

        while (currentLocation != null) {
            LocationNavigation.displayCurrentLocations();

            // Проводим бой
            BattleResult result = Battle.startBattle(player, currentLocation.monster);

            if (result.isVictory()) {
                handleVictory(player, currentLocation);
                // Получаем следующую локацию
                currentLocation = LocationNavigation.getNextLocations();
            } else {
                if (handleDefeat()) {
                    break; // Игрок хочет выйти
                }
                // Если игрок хочет продолжить, начинаем заново
                currentLocation = LocationNavigation.getStartLocation();
            }
        }

        System.out.println("🎉 Все локации исследованы!");
    }

    private static void handleVictory(Character player, LocationAndLore.Location location) {
        monstersDefeated++;

        BattleSystem.restoreHealth(player);
        BattleSystem.checkVictory(monstersDefeated);

        // Предложить замену оружия
        BattleSystem.offerWeaponDrop(player, location.monster);

        LevelManager.levelUpCharacter(player);
        LevelManager.levelUpClass(player);



        printFinalCharacterInfo(player);

        // Пауза перед следующей локацией
        waitForContinue();
    }

    private static boolean handleDefeat() {
        System.out.println("💀 Вы погибли...");
        BattleSystem.playerDefeated();
        monstersDefeated = 0;

        // хочет ли игрок попробовать снова
        while (true) {
            System.out.println("Хотите попробовать снова? (да/нет)");
            String answer = scanner.nextLine().toLowerCase();

            if (answer.equals("да") || answer.equals("д")) {
                try {
                    getCharacter(); // Создаем нового персонажа
                    return false; // Продолжаем игру
                } catch (CharacterCreationException e) {
                    System.out.println("Ошибка создания персонажа: " + e.getMessage());
                    return true; // Выход при ошибке
                }
            } else if (answer.equals("нет") || answer.equals("н")) {
                return true; // Выход из игры
            } else {
                System.out.println("Пожалуйста, введите 'да' или 'нет'");
            }
        }
    }

    private static void waitForContinue() {

        System.out.println("\n\nНажмите Enter для продолжения... \n");
        try {
            System.in.read();
        } catch (Exception e) {
            // Игнорируем ошибки ввода
        }
    }

    public static void printFinalCharacterInfo(Character character) {

        StringBuilder classesBuilder = new StringBuilder();
        List<CharacterClassLevel> classes = character.getClasses();

        for (int i = 0; i < classes.size(); i++) {
            if (i > 0) {
                classesBuilder.append(", ");
            }
            classesBuilder.append(classes.get(i).toString());
        }
        String classesString = classesBuilder.toString();

        System.out.printf("""
                    +------------------------------------------+
                    |          ИНФОРМАЦИЯ О ПЕРСОНАЖЕ          |
                    +------------------------------------------+
                    | Имя: %-35s |
                    | Уровень персонажа: %-21d |
                    | Классы: %-32s |
                    +------------------------------------------+
                    | Здоровье: %-30.1f |
                    | Сила: %-34d |
                    | Ловкость: %-30d |
                    | Выносливость: %-26d |
                    +------------------------------------------+
                    | Оружие: %-32s |
                    | Урон оружия: %-27d |
                    | Бонус урона от силы: %-19d |
                    | Общий урон: %-28d |
                    +------------------------------------------+
                    """,
                character.getName(),
                character.getCharacterLevel(),
                classesString,
                character.getHealth(),
                character.getStrength(),
                character.getAgility(),
                character.getEndurance(),
                character.getMainClass().getStartWeapon().getName(),
                character.getMainClass().getStartWeapon().getDamage(),
                character.getStrength(),
                character.getTotalDamage()
        );
    }
}
