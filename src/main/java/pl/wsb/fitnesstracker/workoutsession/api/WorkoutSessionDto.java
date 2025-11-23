package pl.wsb.fitnesstracker.workoutsession.api;

import jakarta.annotation.Nullable;

public record WorkoutSessionDto(
        @Nullable Long id,
        long trainingId,
        String timestamp,
        double startLatitude,
        double startLongitude,
        double endLatitude,
        double endLongitude,
        double altitude
) {}
