package com.dailycodework.universalpetcare.controller;

import com.dailycodework.universalpetcare.dto.EntityConverter;
import com.dailycodework.universalpetcare.dto.UserDto;
import com.dailycodework.universalpetcare.model.User;
import com.dailycodework.universalpetcare.request.RegistrationRequest;
import com.dailycodework.universalpetcare.request.UserUpdateRequest;
import com.dailycodework.universalpetcare.responce.UserResponse;
import com.dailycodework.universalpetcare.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dailycodework.universalpetcare.utils.FeedbackMessages.*;
import static com.dailycodework.universalpetcare.utils.UrlMapping.*;

@RestController
@RequestMapping(USERS)
public class UserController {

    private final UserService userService;
    private final EntityConverter<User, UserDto> entityConverter;

    public UserController(UserService userService, EntityConverter entityConverter) {

        this.userService = userService;
        this.entityConverter = entityConverter;
    }

    @PostMapping(REGISTER)
    public ResponseEntity<UserResponse> registration(@RequestBody RegistrationRequest request) {

        User user = userService.registration(request);
        UserDto registeredUser = entityConverter.mapEntityToDto(user, UserDto.class);
        UserResponse response = new UserResponse();
        response.setUserDto(registeredUser);
        response.setMessage(SUCCESS);

        return ResponseEntity.ok(response);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<UserResponse> update(@PathVariable(name = "userId") Long userId,
                                               @RequestBody UserUpdateRequest request) {

        User user = userService.update(userId, request);
        UserDto updatedUser = entityConverter.mapEntityToDto(user, UserDto.class);
        UserResponse response = new UserResponse();
        response.setUserDto(updatedUser);
        response.setMessage(UPDATED);

        return ResponseEntity.ok(response);
    }

    @GetMapping(USER_ID)
    public ResponseEntity<UserResponse> findUserById(@PathVariable(name = "userId") Long userId) {

        Optional<User> user = userService.findUserById(userId);
        UserDto userDto = entityConverter.mapEntityToDto(user.get(), UserDto.class);
        UserResponse response = new UserResponse();
        response.setUserDto(userDto);
        response.setMessage(FOUND);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(DELETE_USER_BY_ID)
    public ResponseEntity<String> delete(@PathVariable(name = "userId") Long userId) {

        userService.deleteUserById(userId);
        return ResponseEntity.ok(DELETED);
    }

    @GetMapping(ALL_USERS)
    public ResponseEntity<List<UserDto>> findAllUsers() {

        List<User> allUsers = userService.findAllUsers();

        List<UserDto> response = allUsers
                .stream()
                .map(e -> entityConverter.mapEntityToDto(e, UserDto.class))
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Long> count() {

        Long count = userService.count();

        return ResponseEntity.ok(count);
    }
}
