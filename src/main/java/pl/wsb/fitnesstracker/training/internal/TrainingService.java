package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;
import pl.wsb.fitnesstracker.user.internal.UserServiceImpl;

import java.util.List;

/**
 * The type Training service.
 */
@Service
@RequiredArgsConstructor
public class TrainingService {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;
    private final UserServiceImpl userService;

    /**
     * Gets all trainings.
     *
     * @return the all trainings
     */
    public List<TrainingDto> getAllTrainings() {
        return trainingRepository.findAll().stream()
                .map(trainingMapper::toTrainingDto)
                .toList();
    }

    /**
     * Gets trainings by user id.
     *
     * @param userId the user id
     * @return the trainings by user id
     */
    public List<TrainingDto> getTrainingsByUserId(Long userId) {
        return userService.getUser(userId)
                .map(user -> trainingRepository.findAllByUser(user).stream()
                        .map(trainingMapper::toTrainingDto)
                        .toList())
                .orElse(List.of());
    }
}
