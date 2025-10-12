package com.gamebattle.gameLogic;

/**
 *  Класс для описания запуска и проведения игры
 */

import com.gamebattle.Location.LocationAndLore;
import com.gamebattle.character.Character;
import com.gamebattle.character.CharacterClassLevel;
import com.gamebattle.character.LevelManager;
import com.gamebattle.customExceptions.CharacterCreationException;
import com.gamebattle.gameUtils.SleepTime;
import java.util.List;
import java.util.Scanner;

public class StartGame {

    private static Character player;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws CharacterCreationException {
        GameState.resetAll();

//        welcomeMessage();
//        getGameInformation();
        getCharacter();
//        getIntroductionInformation();
        startGameLoop();
    }

    public static void welcomeMessage() {
        SleepTime.sleepSeconds(1);
        System.out.println("\n\n====================================================================");
        SleepTime.sleep(300);
        System.out.println("\nДобро пожаловать в Epic Battle Game");
    }

    public static void getGameInformation() {
        SleepTime.sleepSeconds(1);
        System.out.println("\nПравила игры: \n");
        SleepTime.sleepSeconds(1);

        String[] rules = {
                "1. В начале игрок выбирает класс персонажа (воин, варвар или разбойник).",
                "   У персонажа есть 3 атрибута:",
                "   • Сила: значение силы прибавляется к урону от оружия.",
                "   • Ловкость: повышает шанс попасть или уклониться от атаки.",
                "   • Выносливость: значение выносливости прибавляется к здоровью при каждом повышении уровня.",
                "   У нового персонажа они равны случайному числу от 1 до 3 включительно.",
                "",
                "2. Персонаж сражается со случайным монстром:",
                "   Бой идёт по ходам, персонаж и монстр атакуют друг друга по очереди. Первым ходит тот, у кого выше",
                "   ловкость. Если она одинаковая, то первым ходит персонаж.",
                "   • При атаке сначала вычисляется шанс попадания: берётся случайное число от единицы до суммы",
                "     ловкости атакующего и цели. Если это число меньше или равно ловкости цели - атака промахнулась",
                "     (в информации хода ты будешь видеть какое число выпало и какое требовалось для успешной атаки).",
                "   • Если атака попала, то считается изначальный урон: это урон оружия атакующего + его сила.",
                "   • Применяются все эффекты на атаки у атакующего (Порыв к действию, Ярость и другие).",
                "   • Применяются все эффекты на урон цели (Каменная кожа, Щит и другие).",
                "   • Если итоговый урон больше 0 - его значение вычитается из здоровья цели.",
                "   • Если здоровье цели опустилось до 0 или ниже - бой заканчивается победой атакующего.",
                "   • Если цель выжила, то теперь её очередь атаковать. Переходим к пункту 1.",
                "   • После каждого боя здоровье персонажа восстанавливается до максимума. Затем игроку предлагают",
                "     повысить уровень персонажа.",
                "   • Как и в \"серьёзных\" RPG, у нас есть возможность мультикласса:",
                "     при повышении уровня игрок может выбрать не только тот класс, с которым он начал игру, но и любой",
                "     другой. Тогда он получит +1 уровень в этом классе, и все бонусы от этого уровня.",
                "   • Максимальный суммарный уровень персонажа - 3. Когда он достигнут, игроку больше не предлагают",
                "     повысить уровень, после боя только восстанавливается здоровье.",
                "",
                "4. Если персонаж проиграл - игроку предлагается создать нового персонажа.",
                "5. Если персонаж победил 5 монстров подряд - игра пройдена."
        };

        for (String line : rules) {
            System.out.println(line);
            SleepTime.sleep(300);
        }

        SleepTime.sleepSeconds(1);
        System.out.println("\nНа этом всё, желаю удачи в прохождении игры!");
        SleepTime.sleep(300);
        System.out.println("====================================================================\n");
    }

    public static void getIntroductionInformation() {
        SleepTime.sleepSeconds(1);
        LocationAndLore.getIntroduction();
    }

    public static void getCharacter() throws CharacterCreationException {
        SleepTime.sleepSeconds(1);
        System.out.println("\nИдёт загрузка...");

        SleepTime.sleepSeconds(2);
        System.out.println("\nВам нужно создать игрового персонажа.");
        SleepTime.sleep(300);
        player = CreateCharacter.createNewCharacter();
        GameState.setCurrentPlayer(player);

        SleepTime.sleep(300);
        System.out.println("Загружаем локации...");
        SleepTime.sleep(500);
        System.out.println("Спавним монстров...");
        SleepTime.sleep(700);
        System.out.println("Наделяем вас силой...");
        SleepTime.sleepSeconds(1);
        System.out.println("\nИгра начинается!\n");
        SleepTime.sleepSeconds(1);
    }

    public static void startGameLoop() {
        // Начинаем с первой локации
        LocationAndLore.Location currentLocation = LocationNavigation.getStartLocation();
        boolean gameCompleted = false;

        while (currentLocation != null && !gameCompleted) {
            LocationNavigation.displayCurrentLocations();

            // Проводим бой
            BattleResult result = Battle.startBattle(player, currentLocation.monster);

            if (result.isVictory()) {
                gameCompleted = handleVictory(player, currentLocation); // ← ИСПОЛЬЗОВАТЬ возвращаемое значение
                if (!gameCompleted) {
                    // Получаем следующую локацию только если игра не завершена
                    currentLocation = LocationNavigation.getNextLocations();
                }
            } else {
                if (handleDefeat(player)) {
                    break; // Игрок хочет выйти
                }
                // Если игрок хочет продолжить, начинаем заново
                currentLocation = LocationNavigation.getStartLocation();
            }
        }

        if (gameCompleted) {
            System.out.println("🎉 Игра завершена!");
        } else if (currentLocation == null) {
            System.out.println("🎉 Все локации исследованы!");
        }
    }

    // Метод для полного сброса состояния игры
    private static void resetEverything() {
        System.out.println("\n🌀 Полный сброс игры...");
        SleepTime.sleepSeconds(1);

        // Сбрасываем все статические состояния
        GameState.resetAll();

        // Сбрасываем текущего игрока
        player = null;

        // Сбрасываем все менеджеры и системы
        BattleSystem.monstersDefeated = 0;
    }

    private static boolean handleVictory(Character player, LocationAndLore.Location location) {
        // Проверяем, не завершена ли уже игра
        if (BattleSystem.monstersDefeated >= 5) {
            return true; // ← ДОБАВИТЬ возврат
        }

        BattleSystem.monstersDefeated++;

        BattleSystem.restoreHealth(player);

        // Проверяем победу и выходим если игра завершена
        if (BattleSystem.checkVictory(player)) {
            return true; // ← ДОБАВИТЬ возврат
        }

        // Предложить замену оружия (только если игра не завершена)
        BattleSystem.offerWeaponDrop(player, location.monster);

        // Повышение уровня только если игра не завершена
        LevelManager.levelUpCharacter(player);
        LevelManager.levelUpClass(player);

        printFinalCharacterInfo(player);

        // Пауза перед следующей локацией
        waitForContinue();

        // возврат (игра продолжается)
        return false;
    }

    private static boolean handleDefeat(Character character) {
        System.out.printf("""
                    %n
                    +------------------------------------------+
                    |                КОНЕЦ  ИГРЫ               |
                    +------------------------------------------+
                    |          💀 Ваш персонаж мёртв           |
                    |                                          |
                    | Имя: %-35s |
                    | Уровень персонажа: %-21d |
                    | Оружие: %-32s |
                    +------------------------------------------+
                    """,
                character.getName(),
                character.getCharacterLevel(),
                character.getMainClass().getStartWeapon().getName()
        );

        // ПОЛНЫЙ СБРОС ВСЕГО СОСТОЯНИЯ ИГРЫ
        resetEverything();

        System.out.println("\n\n=========================================================\n");

        // хочет ли игрок попробовать снова
        while (true) {
            System.out.print("Хотите попробовать снова? (да/нет):  ");
            String answer = scanner.nextLine().toLowerCase();

            if (answer.equals("да") || answer.equals("д") || answer.equals("yes") || answer.equals("y")) {
                try {
                    // Полный сброс перед созданием нового персонажа
                    resetEverything();
                    getCharacter();
                    return false; // игра продолжается
                } catch (CharacterCreationException e) {
                    System.out.println("Ошибка создания персонажа: " + e.getMessage());
                    return true;
                }
            } else if (answer.equals("нет") || answer.equals("н") || answer.equals("no") || answer.equals("n")) {
                return true;
            } else {
                System.out.println("Пожалуйста, введите 'да' или 'нет'");
            }
        }
    }

    public static boolean gameIsOver(Character character) {
        System.out.printf("""
                    %n
                    +------------------------------------------+
                    |              ИГРА ПРОЙДЕНА               |
                    +------------------------------------------+
                    |         Поздравляем с победой!           |
                    |      Вы победили 5 монстров подряд!      |
                    +------------------------------------------+
                    """
        );

        printFinalCharacterInfo(character);

        // полный сброс состояния игры
        resetEverything();

        System.out.println("\n\n============================================\n");

        // хочет ли игрок попробовать снова
        while (true) {
            System.out.print("Хотите попробовать снова? (да/нет):  ");
            String answer = scanner.nextLine().toLowerCase();

            if (answer.equals("да") || answer.equals("д") || answer.equals("yes") || answer.equals("y")) {
                try {
                    // Полный сброс перед созданием нового персонажа
                    resetEverything();
                    getCharacter();
                    return false; // игра продолжается
                } catch (CharacterCreationException e) {
                    System.out.println("Ошибка создания персонажа: " + e.getMessage());
                    return true; // игра завершается
                }
            } else if (answer.equals("нет") || answer.equals("н") || answer.equals("no") || answer.equals("n")) {
                return true; // игра завершается
            } else {
                System.out.println("Пожалуйста, введите 'да' или 'нет'");
            }
        }
    }

    private static void waitForContinue() {
        System.out.println("\n\nНажмите Enter для продолжения... ");
        try {
            scanner.nextLine();
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

        String[] characterInfo = {
                "",
                "+------------------------------------------+",
                "|          ИНФОРМАЦИЯ О ПЕРСОНАЖЕ          |",
                "+------------------------------------------+",
                String.format("| Имя: %-35s |", character.getName()),
                String.format("| Уровень персонажа: %-21d |", character.getCharacterLevel()),
                String.format("| Классы: %-32s |", classesString),
                "+------------------------------------------+",
                String.format("| Здоровье: %-30.1f |", character.getHealth()),
                String.format("| Сила: %-34d |", character.getStrength()),
                String.format("| Ловкость: %-30d |", character.getAgility()),
                String.format("| Выносливость: %-26d |", character.getEndurance()),
                "+------------------------------------------+",
                String.format("| Оружие: %-32s |", character.getMainClass().getStartWeapon().getName()),
                String.format("| Урон оружия: %-27d |", character.getMainClass().getStartWeapon().getDamage()),
                String.format("| Бонус урона от силы: %-19d |", character.getStrength()),
                String.format("| Общий урон: %-28d |", character.getMainClass().getStartWeapon().getDamage() +
                                                        character.getStrength()),
                "+------------------------------------------+"
        };

        for (String line : characterInfo) {
            System.out.println(line);
            SleepTime.sleep(300);
        }
    }
}