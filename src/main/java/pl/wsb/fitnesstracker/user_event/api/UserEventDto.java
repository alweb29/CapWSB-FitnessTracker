package pl.wsb.fitnesstracker.user_event.api;

import pl.wsb.fitnesstracker.event.api.Event;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user_event.internal.Status;

public record UserEventDto(
        Long id,
        User user,
        Event event,
        Status status
) {
}
