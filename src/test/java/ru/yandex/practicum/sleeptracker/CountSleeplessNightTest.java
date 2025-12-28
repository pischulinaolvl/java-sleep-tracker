package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountSleeplessNightTest {
    private static List<SleepingSession> sessions;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy");

    @BeforeAll
    public static void beforeAll() {
        // Подготовка
        sessions = new ArrayList<>();
        sessions.add(new SleepingSession("01.10.25 22:15", "02.10.25 08:00", SleepType.GOOD));
        sessions.add(new SleepingSession("02.10.25 13:13", "02.10.25 14:14", SleepType.GOOD));
        sessions.add(new SleepingSession("02.10.25 23:00", "03.10.25 08:00", SleepType.NORMAL));
        sessions.add(new SleepingSession("03.10.25 14:30", "03.10.25 15:20", SleepType.NORMAL));
        sessions.add(new SleepingSession("03.10.25 23:30", "04.10.25 06:20", SleepType.GOOD));
        sessions.add(new SleepingSession("04.10.25 23:30", "05.10.25 06:20", SleepType.GOOD));
        sessions.add(new SleepingSession("07.10.25 23:30", "08.10.25 06:20", SleepType.GOOD));
    }

    @Test
    @DisplayName("Проверка метода findMinStartDate")
    public void testFindMinStartDate() {
        //Исполнение
        Optional<LocalDateTime> minStartDate = CountSleeplessNight.findMinStartDate(sessions);
        // Проверка
        Assertions.assertEquals(minStartDate.orElseGet(() -> LocalDateTime.parse("01.01.01 00:00", DATE_TIME_FORMATTER)), LocalDateTime.parse("01.10.25 22:15", DATE_TIME_FORMATTER));
    }

    @Test
    @DisplayName("Проверка метода findMaxEndDate")
    public void testFindMaxEndDate() {
        //Исполнение
        Optional<LocalDateTime> maxEndDate = CountSleeplessNight.findMaxEndDate(sessions);
        // Проверка
        Assertions.assertEquals(maxEndDate.orElseGet(() -> LocalDateTime.parse("01.01.01 00:00", DATE_TIME_FORMATTER)), LocalDateTime.parse("08.10.25 06:20", DATE_TIME_FORMATTER));
    }

    @Test
    @DisplayName("Проверка метода findNight")
    public void testFindNight() {
        // Проверка
        Assertions.assertEquals(CountSleeplessNight.findNight(sessions.get(0)).orElseGet(() -> LocalDate.parse("01.01.01", DATE_FORMATTER)), LocalDate.parse("02.10.25", DATE_FORMATTER));
        Assertions.assertEquals(CountSleeplessNight.findNight(sessions.get(1)).isEmpty(), true);
    }
}
