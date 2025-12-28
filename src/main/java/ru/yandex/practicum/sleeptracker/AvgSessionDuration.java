package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

class AvgSessionDuration implements SleepAnalysisFunction {
    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(0, "Минимальная продолжительность сессии");
        }

        double avgDirection = sessions
                .stream()
                .mapToInt(session -> (int) Duration.between(session.getStartDate(), session.getEndDate()).toMinutes())
                .average()
                .orElse(0);

        return new SleepAnalysisResult((int)avgDirection,"Средняя продолжительность сессии");
    }
}