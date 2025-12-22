package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

class CountBadSession implements SleepAnalysisFunction {
    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(0, "Минимальная продолжительность сессии");
        }

        long count = sessions
                .stream()
                .filter(session -> session.getSleepType() == SleepType.BAD)
                .count();

        return new SleepAnalysisResult((int) count,"Количество сессий с плохим сном");
    }
}