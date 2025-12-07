package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.nio.channels.FileChannel;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

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
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUserByNameAndLastName(String name, String lastName) {
        return userRepository.findByFirstNameAndLastName(name, lastName);
    }


    public Map<Long, String> getAllUserIdsWithEmail(String email) {
        return userRepository.findAll().stream()
                .filter(user -> user.getEmail().contains(email))
                .collect(Collectors.toMap(User::getId, User::getFirstName));
    }

    public List<User> getAllUsersByAgeGreaterThan(int age) {
        return userRepository.findAll().stream()
                .filter(user -> {
                    Period ageOfUser = Period.between(user.getBirthdate(), LocalDate.now());
                    return age < ageOfUser.getYears();
                })
                .toList();
    }

//    public List<User> updateLastNameOfAllUsers(String lastName) {
//        return  userRepository.findAll().stream()
//                .map(user -> createUser(new User(user.getFirstName(),
//                        lastName,
//                        user.getBirthdate(),
//                        user.getEmail())));
//    }
}