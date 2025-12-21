package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type User service.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.findById(userId).ifPresent(userRepository::delete);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getUsersByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUserByNameAndLastName(String name, String lastName) {
        return userRepository.findByFirstNameAndLastName(name, lastName);
    }


    /**
     * Gets all user ids with email.
     *
     * @param email the email
     * @return the all user ids with email
     */
    public Map<Long, String> getAllUserIdsWithEmail(String email) {
        return userRepository.findAll().stream()
                .filter(user -> user.getEmail().contains(email))
                .collect(Collectors.toMap(User::getId, User::getFirstName));
    }

    /**
     * Gets all users by age greater than.
     *
     * @param date the date
     * @return the all users by age greater than
     */
    public List<User> getAllUsersByAgeGreaterThan(LocalDate date) {
        return userRepository.findAll().stream()
                .filter(user -> date.isAfter(user.getBirthdate()))
                .toList();
    }

    /**
     * Update user user dto.
     *
     * @param user    the user
     * @param userDto the user dto
     * @return the user dto
     */
    public UserDto updateUser(User user, UserDto userDto) {
        User updatedUser = userMapper.updateUser(user, userDto);
        return userMapper.toDto(userRepository.save(updatedUser));
    }
}