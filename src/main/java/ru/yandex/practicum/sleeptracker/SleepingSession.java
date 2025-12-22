package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SleepingSession {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private SleepType sleepType;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public SleepingSession(String startDate, String endDate, SleepType sleepType) {
        this.startDate = LocalDateTime.parse(startDate, DATE_TIME_FORMATTER);
        this.endDate = LocalDateTime.parse(endDate, DATE_TIME_FORMATTER);
        this.sleepType = sleepType;
    }

    public SleepingSession(String sleep) {
        String[] arr = sleep.split(";");
        this.startDate = LocalDateTime.parse(arr[0], DATE_TIME_FORMATTER);
        this.endDate = LocalDateTime.parse(arr[1], DATE_TIME_FORMATTER);
        this.sleepType = SleepType.valueOf(arr[2]);
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public SleepType getSleepType() {
        return sleepType;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setSleepType(SleepType sleepType) {
        this.sleepType = sleepType;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String toString(){
        return "Начало сна: " + startDate.format(DATE_TIME_FORMATTER) + ". Конец сна:  " + endDate.format(DATE_TIME_FORMATTER) + ". Состояние сна: " + sleepType.toString();
    }
}
