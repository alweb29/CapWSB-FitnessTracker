package pl.wsb.fitnesstracker.healthmetrics.api;

import jakarta.annotation.Nullable;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

public record HealthMetricsDto(@Nullable Long id,
                               User user,
                               LocalDate date,
                               float weight,
                               float height,
                               int heartRate) {
}
