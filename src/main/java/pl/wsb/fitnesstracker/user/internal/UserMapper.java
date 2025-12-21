package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserIdEmailDto;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;

/**
 * The type User mapper.
 */
@Component
public class UserMapper {

    /**
     * To dto user dto.
     *
     * @param user the user
     * @return the user dto
     */
    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * To user id email dto user id email dto.
     *
     * @param user the user
     * @return the user id email dto
     */
    UserIdEmailDto toUserIdEmailDto(User user) {
        return new UserIdEmailDto(user.getId(), user.getEmail());
    }

    /**
     * To simple dto user simple dto.
     *
     * @param user the user
     * @return the user simple dto
     */
    UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    /**
     * To user user.
     *
     * @param userDto the user dto
     * @return the user
     */
    public User toUser(UserDto userDto) {
        return new User(userDto.firstName(), userDto.lastName(), userDto.birthdate(), userDto.email());
    }

    /**
     * Update user user.
     *
     * @param user    the user
     * @param userDto the user dto
     * @return the user
     */
    public User updateUser(User user, UserDto userDto) {
        return new User(user.getId(), userDto.firstName(), userDto.lastName(), userDto.birthdate(), userDto.email());
    }
}
