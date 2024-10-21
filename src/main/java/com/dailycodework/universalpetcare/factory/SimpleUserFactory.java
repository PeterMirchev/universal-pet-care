package com.dailycodework.universalpetcare.factory;

import com.dailycodework.universalpetcare.model.User;
import com.dailycodework.universalpetcare.request.RegistrationRequest;

public class SimpleUserFactory implements UserFactory{
    @Override
    public User createUser(RegistrationRequest registrationRequest) {
        return null;
    }
}
