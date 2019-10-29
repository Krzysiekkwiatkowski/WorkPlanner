package com.programs.data;

import java.io.*;
import java.time.DayOfWeek;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Setting {
    private File file;
    private static Map<DayOfWeek, Map<String, Integer>> requiredShifts;
    private static Map<DayOfWeek, Map<String, Integer>> optionalShifts;
    private Scanner scanner;

    public Setting() {
        file = new File("setting.txt");
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        requiredShifts = new LinkedHashMap<>();
        optionalShifts = new LinkedHashMap<>();
        DayOfWeek[] daysOfWeeks = DayOfWeek.values();
        for (int i = 0; i < daysOfWeeks.length; i++) {
            requiredShifts.put(daysOfWeeks[i], new LinkedHashMap<>());
            optionalShifts.put(daysOfWeeks[i], new LinkedHashMap<>());
        }
        loadData();
    }

    private void saveData() {
        FileWriter writer;
        DayOfWeek[] daysOfWeek = DayOfWeek.values();
        Map<String, Integer> actualMap;
        try {
            writer = new FileWriter(file);
            for (int i = 0; i < daysOfWeek.length; i++) {
                writer.append(daysOfWeek[i].toString() + "\n");
                actualMap = requiredShifts.get(daysOfWeek[i]);
                for (Shift shift : Shift.getShifts()) {
                    writer.append(shift.getHours() + "," + actualMap.get(shift.getHours()) + "\n");
                }
                actualMap = optionalShifts.get(daysOfWeek[i]);
                for (Shift shift : Shift.getShifts()) {
                    writer.append(shift.getHours() + "," + actualMap.get(shift.getHours()) + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    private void loadData() {
        DayOfWeek dayOfWeek;
        String input;
        while (scanner.hasNextLine()) {
            input = scanner.nextLine().trim();
            if (input.length() > 0) {
                dayOfWeek = DayOfWeek.valueOf(input);
                requiredShifts.get(dayOfWeek);
                optionalShifts.get(dayOfWeek);
                loadData(dayOfWeek, requiredShifts);
                loadData(dayOfWeek, optionalShifts);
            }
        }
    }

    private void loadData(DayOfWeek dayOfWeek, Map<DayOfWeek, Map<String, Integer>> actualMap) {
        String input;
        String hours;
        Integer numberOfPeople;
        for (int i = 0; i < Shift.getShifts().size(); i++) {
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();
                String[] data = input.split(",");
                hours = data[0];
                numberOfPeople = Integer.valueOf(data[1]);
                actualMap.get(dayOfWeek).put(hours, numberOfPeople);
            }
        }
    }

    public void saveSetting(){
        saveData();
    }

    public Map<DayOfWeek, Map<String, Integer>> getRequiredShifts() {
        return requiredShifts;
    }

    public Map<DayOfWeek, Map<String, Integer>> getOptionalShifts() {
        return optionalShifts;
    }
}
