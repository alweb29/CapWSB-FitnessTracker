package pl.wsb.fitnesstracker.user.internal;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserIdEmailDto;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;

import java.time.LocalDate;
import java.util.List;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for retrieving and creating users.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    /**
     * Gets all users.
     *
     * @return the all users
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Gets all simple users.
     *
     * @return the all simple users
     */
    @GetMapping("simple")
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    /**
     * Gets user by id.
     *
     * @param userId the user id
     * @return the user by id
     */
    @GetMapping("{userId}")
    public UserDto getUserById(@PathVariable long userId) {
        return userService.getUser(userId)
                .map(userMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Update user user dto.
     *
     * @param userId  the user id
     * @param userDto the user dto
     * @return the user dto
     */
    @PutMapping("{userId}")
    public UserDto updateUser(@PathVariable long userId, @RequestBody UserDto userDto) {
        User user = userService.getUser(userId).orElseThrow(EntityNotFoundException::new);
        return userService.updateUser(user, userDto);
    }

    /**
     * Gets user by name and last name.
     *
     * @param name     the name
     * @param lastName the last name
     * @return the user by name and last name
     */
    @GetMapping("name/{name}/lastname/{lastName}")
    public List<UserDto> getUserByNameAndLastName(@PathVariable String name, @PathVariable String lastName) {
        return userService.getUserByNameAndLastName(name, lastName)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Gets user by email.
     *
     * @param email the email
     * @return the user by email
     */
    @GetMapping("email")
    public List<UserIdEmailDto> getUserByEmail(@RequestParam String email) {
        return userService.getUsersByEmail(email).stream()
                .map(userMapper::toUserIdEmailDto)
                .toList();
    }

    /**
     * Create user response entity.
     *
     * @param userDto the user dto
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto dto = userMapper.toDto(userService.createUser(userMapper.toUser(userDto)));
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    /**
     * Delete user response entity.
     *
     * @param userId the user id
     * @return the response entity
     */
    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Gets all users by age greater than.
     *
     * @param time the time
     * @return the all users by age greater than
     */
    @GetMapping("older/{time}")
    public List<UserDto> getAllUsersByAgeGreaterThan(@PathVariable LocalDate time) {
        return userService.getAllUsersByAgeGreaterThan(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}

