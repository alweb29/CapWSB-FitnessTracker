package pl.wsb.fitnesstracker.user.internal;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PostLoad;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserIdEmailDto;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;

import java.util.List;
import java.util.Map;

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

    @GetMapping("name/{name}/lastname/{lastName}")
    public List<UserDto> getUserByNameAndLastName(@PathVariable String name, @PathVariable String lastName) {
        return userService.getUserByNameAndLastName(name, lastName)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("email/{email}")
    public UserIdEmailDto getUserById(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .map(userMapper::toUserIdEmailDto)
                .orElseThrow(EntityNotFoundException::new);
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
    public List<UserDto> getAllUsersByAgeGreaterThan(@PathVariable int time) {
        return userService.getAllUsersByAgeGreaterThan(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}

