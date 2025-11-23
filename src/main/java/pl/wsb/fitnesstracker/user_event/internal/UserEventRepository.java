package pl.wsb.fitnesstracker.user_event.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user_event.api.UserEvent;

public interface UserEventRepository extends JpaRepository<UserEvent, Long> {
}
