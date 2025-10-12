package com.gamebattle.gameLogic;

/**
 *  Класс для описания результатов боя
 */

public class BattleResult {
    private boolean victory;
    private int playerAttacks;
    private int playerHits;
    private int monsterAttacks;
    private int monsterHits;
    private int turnCounter;

    public BattleResult() {
        this.turnCounter = 0;
    }

    public void addMonsterAttack(boolean hit) {
        this.monsterAttacks++;
        if (hit) {
            this.monsterHits++;
        }
    }

    public void addPlayerAttack(boolean hit) {
        this.playerAttacks++;
        if (hit) {
            this.playerHits++;
        }
    }

    public int getTurnCount() {
        return turnCounter;
    }

    // Метод для увеличения счетчика ходов
    public void incrementTurnCount() {
        this.turnCounter++;
    }

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

    public int getPlayerAttacks() {
        return playerAttacks;
    }

    public int getPlayerHits() {
        return playerHits;
    }

    public int getMonsterAttacks() {
        return monsterAttacks;
    }

    public int getMonsterHits() {
        return monsterHits;
    }
}