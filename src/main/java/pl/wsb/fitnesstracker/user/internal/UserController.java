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

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("simple")
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    @GetMapping("{userId}")
    public UserDto getUserById(@PathVariable long userId) {
        return userService.getUser(userId)
                .map(userMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping("{userId}")
    public UserDto updateUser(@PathVariable long userId, @RequestBody UserDto userDto) {
        User user = userService.getUser(userId).orElseThrow(EntityNotFoundException::new);
        return userService.updateUser(user, userDto);
    }

    @GetMapping("name/{name}/lastname/{lastName}")
    public List<UserDto> getUserByNameAndLastName(@PathVariable String name, @PathVariable String lastName) {
        return userService.getUserByNameAndLastName(name, lastName)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("email")
    public List<UserIdEmailDto> getUserByEmail(@RequestParam String email) {
        return userService.getUsersByEmail(email).stream()
                .map(userMapper::toUserIdEmailDto)
                .toList();
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto dto = userMapper.toDto(userService.createUser(userMapper.toUser(userDto)));
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("older/{time}")
    public List<UserDto> getAllUsersByAgeGreaterThan(@PathVariable LocalDate time) {
        return userService.getAllUsersByAgeGreaterThan(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}

