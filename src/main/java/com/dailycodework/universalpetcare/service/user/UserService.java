package com.dailycodework.universalpetcare.service.user;

import com.dailycodework.universalpetcare.factory.UserFactory;
import com.dailycodework.universalpetcare.model.User;
import com.dailycodework.universalpetcare.repository.UserRepository;
import com.dailycodework.universalpetcare.request.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserFactory userFactory;

    public UserService(UserRepository userRepository,
                       UserFactory userFactory) {

        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    public User add(RegistrationRequest request) {

       return userFactory.createUser(request);
    }
}
