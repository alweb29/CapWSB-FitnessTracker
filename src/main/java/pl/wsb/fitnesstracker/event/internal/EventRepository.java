package pl.wsb.fitnesstracker.event.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.event.api.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findByName(String name);
}
