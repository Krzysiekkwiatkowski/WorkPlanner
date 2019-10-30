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
        boolean loadedFile = true;
        file = new File("setting.txt");
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            loadedFile = false;
            initializeMaps();
            loadDefaultValues();
        }
        if(loadedFile) {
            initializeMaps();
            loadData();
        }
    }

    private void initializeMaps(){
        requiredShifts = new LinkedHashMap<>();
        optionalShifts = new LinkedHashMap<>();
        DayOfWeek[] daysOfWeeks = DayOfWeek.values();
        for (int i = 0; i < daysOfWeeks.length; i++) {
            requiredShifts.put(daysOfWeeks[i], new LinkedHashMap<>());
            optionalShifts.put(daysOfWeeks[i], new LinkedHashMap<>());
        }
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

    private void loadDefaultValues(){
        DayOfWeek[] dayOfWeek = DayOfWeek.values();
        for (int i = 0; i < dayOfWeek.length; i++) {
            for (Shift shift : Shift.getShifts()) {
                switch (shift.getHours()){
                    case "6-14":
                        requiredShifts.get(dayOfWeek[i]).put(shift.getHours(), 3);
                        optionalShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                        break;
                    case "7:30-15:30":
                        if(dayOfWeek[i] == DayOfWeek.SATURDAY || dayOfWeek[i] == DayOfWeek.SUNDAY){
                            requiredShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                            optionalShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                        } else {
                            requiredShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                            optionalShifts.get(dayOfWeek[i]).put(shift.getHours(), 2);
                        }
                        break;
                    case "10-14":
                        if(dayOfWeek[i] == DayOfWeek.SATURDAY || dayOfWeek[i] == DayOfWeek.SUNDAY){
                            requiredShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                            optionalShifts.get(dayOfWeek[i]).put(shift.getHours(), 2);
                        } else {
                            requiredShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                            optionalShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                        }
                        break;
                    case "6-22":
                        if(dayOfWeek[i] == DayOfWeek.FRIDAY || dayOfWeek[i] == DayOfWeek.SATURDAY || dayOfWeek[i] == DayOfWeek.SUNDAY){
                            requiredShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                            optionalShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                        } else {
                            requiredShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                            optionalShifts.get(dayOfWeek[i]).put(shift.getHours(), 1);
                        }
                        break;
                    case "14-22":
                        requiredShifts.get(dayOfWeek[i]).put(shift.getHours(), 3);
                        optionalShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                        break;
                    case "14-18":
                        requiredShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                        optionalShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                        break;
                    case "15:30-23:30":
                        requiredShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                        optionalShifts.get(dayOfWeek[i]).put(shift.getHours(), 1);
                        break;
                    case "17-1":
                        requiredShifts.get(dayOfWeek[i]).put(shift.getHours(), 1);
                        optionalShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                        break;
                    case "18-2":
                        if(dayOfWeek[i] == DayOfWeek.FRIDAY || dayOfWeek[i] == DayOfWeek.SATURDAY){
                            requiredShifts.get(dayOfWeek[i]).put(shift.getHours(), 1);
                            optionalShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                        } else {
                            requiredShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                            optionalShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                        }
                        break;
                    case "22-6":
                        if(dayOfWeek[i] == DayOfWeek.SATURDAY){
                            requiredShifts.get(dayOfWeek[i]).put(shift.getHours(), 3);
                            optionalShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                        } else {
                            requiredShifts.get(dayOfWeek[i]).put(shift.getHours(), 2);
                            optionalShifts.get(dayOfWeek[i]).put(shift.getHours(), 0);
                        }
                        break;
                }
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
