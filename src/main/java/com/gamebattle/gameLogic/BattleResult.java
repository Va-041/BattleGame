package com.gamebattle.gameLogic;

public class BattleResult {
    private boolean victory;
    private int playerAttacks;
    private int playerHits;
    private int monsterAttacks;
    private int monsterHits;

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

//    public double getPlayerAccuracy() {
//        return playerAttacks > 0 ? (double) playerHits / playerAttacks * 100 : 0;
//    }
//
//    public double getMonsterAccuracy() {
//        return monsterAttacks > 0 ? (double) monsterHits / monsterAttacks * 100 : 0;
//    }


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