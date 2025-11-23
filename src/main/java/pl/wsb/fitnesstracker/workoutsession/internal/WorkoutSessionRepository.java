package pl.wsb.fitnesstracker.workoutsession.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wsb.fitnesstracker.workoutsession.api.WorkoutSession;

public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession,Long> {

}
