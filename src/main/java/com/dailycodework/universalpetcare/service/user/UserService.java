package com.dailycodework.universalpetcare.service.user;

import com.dailycodework.universalpetcare.exception.ResourceNotFoundException;
import com.dailycodework.universalpetcare.factory.UserFactory;
import com.dailycodework.universalpetcare.model.User;
import com.dailycodework.universalpetcare.repository.UserRepository;
import com.dailycodework.universalpetcare.request.RegistrationRequest;
import com.dailycodework.universalpetcare.request.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final UserFactory userFactory;

    public UserService(UserRepository userRepository, UserFactory userFactory) {

        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    @Override
    public User registration(RegistrationRequest request) {

       return userFactory.createUser(request);
    }

    @Override
    public User update(Long userId, UserUpdateRequest request) {

        Optional<User> user = findUserById(userId);

        mapToUser(request, user);

        return userRepository.save(user.get());
    }

    @Override
    public void deleteUserById(Long userId) {

        Optional<User> user = findUserById(userId);
        user.ifPresent(userRepository::delete);
    }

    @Override
    public List<User> findAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public Long count() {

        return userRepository.count();
    }

    public Optional<User> findUserById(Long userId) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User Not Found! Invalid user id - %s", userId))));
        return user;
    }

    private static void mapToUser(UserUpdateRequest request, Optional<User> user) {
        user.get().setFirstName(request.getFirstName());
        user.get().setLastName(request.getLastName());
        user.get().setGender(request.getGender());
        user.get().setPhoneNumber(request.getPhoneNumber());
        user.get().setSpecialization(request.getSpecialization());
    }
}
