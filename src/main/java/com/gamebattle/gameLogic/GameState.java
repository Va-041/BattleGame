package com.gamebattle.gameLogic;

/**
 * Класс для управления глобальным состоянием игры
 */

import com.gamebattle.character.Character;

public class GameState {
    private static Character currentPlayer = null;
    private static boolean isNewGame = true;

    public static void resetAll() {
        currentPlayer = null;
        isNewGame = true;
        BattleSystem.resetGameState();
        LocationNavigation.resetUsedLocations();
    }

    public static void setCurrentPlayer(Character player) {
        currentPlayer = player;
        isNewGame = false;
    }

    public static Character getCurrentPlayer() {
        return currentPlayer;
    }

    public static boolean isNewGame() {
        return isNewGame;
    }
}