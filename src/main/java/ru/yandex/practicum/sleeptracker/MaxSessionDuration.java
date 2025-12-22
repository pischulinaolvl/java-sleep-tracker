package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

class MaxSessionDuration implements SleepAnalysisFunction {
    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(0, "Минимальная продолжительность сессии");
        }

        int maxDirection = sessions
                .stream()
                .mapToInt(session -> (int) Duration.between(session.getStartDate(), session.getEndDate()).toMinutes())
                .max()
                .orElse(0);

        return new SleepAnalysisResult( maxDirection,"Максимальная продолжительность сессии");
    }
}