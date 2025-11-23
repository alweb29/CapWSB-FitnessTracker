package pl.wsb.fitnesstracker.event.api;

import java.time.LocalDateTime;

public record EventDto(
        Long id,
        String name,
        String description,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String country,
        String city
) {
}
