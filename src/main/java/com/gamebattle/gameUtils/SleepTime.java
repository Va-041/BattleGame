package com.gamebattle.gameUtils;

public class SleepTime {

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Задержка прервана");
        }
    }

    public static void sleepSeconds(int seconds) {
        sleep(seconds * 1000);
    }
}
