package com.dailycodework.universalpetcare.responce;

import com.dailycodework.universalpetcare.dto.UserDto;
import lombok.Data;

@Data
public class UserResponse {

    private String message;
    private UserDto userDto;
}
