package com.gamebattle.gameLogic;

/**
 *  Класс для описания запуска и проведения игры
 */

import com.gamebattle.character.Character;
import com.gamebattle.character.CharacterClassLevel;
import com.gamebattle.customExceptions.CharacterCreationException;
import java.util.List;

public class StartGame {

    public static void main(String[] args) throws CharacterCreationException {
        welcomeMessage();
        getGameInformation();
        getCharacter();

        getStartLocation();
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
        Character player = CreateCharacter.createNewCharacter();

        printFinalCharacterInfo(player);
    }

    public static void getStartLocation() {
        LocationAndLore.getStartLocation();
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
                    %n""",
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
