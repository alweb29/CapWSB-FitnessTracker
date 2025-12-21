package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.user.internal.UserMapper;

/**
 * The type Training mapper.
 */
@Component
@RequiredArgsConstructor
public class TrainingMapper {

    private final UserMapper userMapper;

    /**
     * To training training.
     *
     * @param trainingDto the training dto
     * @return the training
     */
    public Training toTraining (TrainingDto trainingDto)
    {
        return new Training(
                userMapper.toUser(trainingDto.getUser()),
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed());
    }

    /**
     * To training dto training dto.
     *
     * @param training the training
     * @return the training dto
     */
    public TrainingDto toTrainingDto (Training training)
    {
        return new TrainingDto(training.getId(),
                userMapper.toDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());
    }
}
