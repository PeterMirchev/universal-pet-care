package com.dailycodework.universalpetcare.service.user;

import com.dailycodework.universalpetcare.model.User;
import com.dailycodework.universalpetcare.request.RegistrationRequest;
import com.dailycodework.universalpetcare.request.UserUpdateRequest;

public interface IUserService {

    User registration(RegistrationRequest request);

    User update(Long userId, UserUpdateRequest request);

    void deleteUserById(Long userId);
}
