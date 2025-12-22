package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class SleepTrackerApp {
     public static Scanner scanner = new Scanner(System.in);

     public static void main(String[] args) {
        System.out.println("Введите путь к файлу с данными о ваших снах:");
        //String fileName = scanner.nextLine();
        String fileName = "C:\\Users\\pischulinaov\\IdeaProjects\\java-sleep-tracker\\sleepLogs.txt";

        try {
            List<String> sleepString = Files.readAllLines(Paths.get(fileName));
            List<SleepingSession> sleepingSessionList = sleepString
                    .stream()
                    .map(sleep -> new SleepingSession(sleep))
                    .collect(Collectors.toList());

            List<SleepAnalysisFunction> functions = new ArrayList<>();
            functions.add(new MinSessionDuration());
            functions.add(new MaxSessionDuration());
            functions.add(new AvgSessionDuration());
            functions.add(new CountBadSession());

            functions.stream()
                    .map(function -> function.analyze(sleepingSessionList))
                    .forEach(result -> System.out.println("Была вызвана функция \"" + result.getDescription() + "\". Результат: " + result.getResult()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}