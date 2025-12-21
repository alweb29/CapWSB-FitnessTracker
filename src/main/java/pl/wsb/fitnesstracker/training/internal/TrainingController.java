package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wsb.fitnesstracker.training.api.TrainingDto;

import java.util.List;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for retrieving and creating users.
 */
@RestController
@RequestMapping("/v1/training")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingService trainingService;

    /**
     * Gets all trainings.
     *
     * @return the all trainings
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings();
    }

    /**
     * Gets all trainings for user.
     *
     * @param userId the user id
     * @return the all trainings for user
     */
    @GetMapping("/user/{userId}")
    public List<TrainingDto> getAllTrainingsForUser(@PathVariable("userId") Long userId) {
        return trainingService.getTrainingsByUserId(userId);
    }
}

