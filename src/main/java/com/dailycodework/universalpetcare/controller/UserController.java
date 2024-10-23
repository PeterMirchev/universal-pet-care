package com.dailycodework.universalpetcare.controller;

import com.dailycodework.universalpetcare.dto.EntityConverter;
import com.dailycodework.universalpetcare.dto.UserDto;
import com.dailycodework.universalpetcare.model.User;
import com.dailycodework.universalpetcare.request.RegistrationRequest;
import com.dailycodework.universalpetcare.responce.RegistrationResponse;
import com.dailycodework.universalpetcare.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dailycodework.universalpetcare.utils.FeedbackMessages.SUCCESS;
import static com.dailycodework.universalpetcare.utils.UrlMapping.USERS;

@RestController
@RequestMapping(USERS)
public class UserController {

    private final UserService userService;
    private final EntityConverter<User, UserDto> entityConverter;

    public UserController(UserService userService, EntityConverter entityConverter) {

        this.userService = userService;
        this.entityConverter = entityConverter;
    }

    @PostMapping
    public ResponseEntity<RegistrationResponse> add(@RequestBody RegistrationRequest request) {

        User theUser = userService.add(request);
        UserDto registeredUser = entityConverter.mapEntityToDto(theUser, UserDto.class);
        RegistrationResponse response = new RegistrationResponse();
        response.setUserDto(registeredUser);
        response.setMessage(SUCCESS);

        return ResponseEntity.ok(response);
    }
}
