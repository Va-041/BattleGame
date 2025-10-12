package com.gamebattle.gameLogic;

/**
 *  Класс для описания возможности смены локаций
 */

import com.gamebattle.Location.LocationAndLore;
import com.gamebattle.gameUtils.SleepTime;
import java.util.*;

public class LocationNavigation {

    private static final Random random = new Random();
    private static final Set<Integer> usedLocations = new HashSet<>();
    private static LocationAndLore.Location currentLocation;

    public static void initializeLocations() {
        LocationAndLore.makeLocations();
        usedLocations.clear();
    }

    // Метод для получения стартовой локации
    public static LocationAndLore.Location getStartLocation() {
        initializeLocations();

        int randomNum = random.nextInt(6) + 1;
        usedLocations.add(randomNum);
        currentLocation = LocationAndLore.getLocationById(randomNum);

        return currentLocation;
    }

    public static LocationAndLore.Location getNextLocations() {
        if (usedLocations.size() >= 6) {
            return null; // Все локации использованы
        }

        List<Integer> availableLocations = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            if (!usedLocations.contains(i)) {
                availableLocations.add(i);
            }
        }

        if (availableLocations.isEmpty()) {
            return null;
        }

        int randomIndex = random.nextInt(availableLocations.size());
        int nextLocationId = availableLocations.get(randomIndex);
        usedLocations.add(nextLocationId);
        currentLocation = LocationAndLore.getLocationById(nextLocationId);

        return currentLocation;
    }

    public static void displayCurrentLocations() {
        if (currentLocation == null) {
            System.out.println("Локация не установлена!");
            return;
        }
        displayLocation(currentLocation);
    }

    public static void displayLocation(LocationAndLore.Location location){
        if (location == null) {
            System.out.println("Локация не найдена!");
            return;
        }

        // Вывод pathToLocation с задержкой
        printWithDelay(location.pathToLocation.split("\n"));

        // Вывод description с задержкой
        printWithDelay(location.description.split("\n"));

        int totalWidth = 51;
        int nameLength = location.name.length();
        int padding = (totalWidth - nameLength) / 2;

        // Вывод названия локации в рамке
        System.out.println();
        printWithDelay(new String[]{
                "+-------------------------------------------------+",
                "|" + " ".repeat(padding) + location.name + " ".repeat(totalWidth - nameLength - padding - 2) + "|",
                "+-------------------------------------------------+",
                ""
        });

        // Вывод информации о противнике
        printWithDelay(new String[]{
                "Противник:\t\t\t" + location.monster.getName(),
                "",
                "Характеристики:"
        });
        location.monster.printMonsterParametersWithDelay();

        printWithDelay(new String[]{
                "",
                "Описание:"
        });
        printWithDelay(location.mobDescription.split("\n"));
    }

    //  метод для вывода описания локаций
    private static void printWithDelay(String[] lines) {
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                System.out.println(line);
                SleepTime.sleepSeconds(1);
            }
        }
    }

    // Метод для сброса использованных локаций (для новой игры)
    public static void resetUsedLocations() {
        usedLocations.clear();
        currentLocation = null;
    }

    // Дополнительный метод для получения текущей локации
    public static LocationAndLore.Location getCurrentLocation() {
        return currentLocation;
    }
}