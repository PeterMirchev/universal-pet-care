package com.dailycodework.universalpetcare.factory;

import com.dailycodework.universalpetcare.exception.UserAlreadyExistsException;
import com.dailycodework.universalpetcare.model.User;
import com.dailycodework.universalpetcare.repository.UserRepository;
import com.dailycodework.universalpetcare.request.RegistrationRequest;
import org.springframework.stereotype.Component;

@Component
public class SimpleUserFactory implements UserFactory{

    private final UserRepository userRepository;
    private final VeterinarianFactory veterinarianFactory;
    private final PatientFactory patientFactory;
    private final AdminFactory adminFactory;

    public SimpleUserFactory(UserRepository userRepository,
                             VeterinarianFactory veterinarianFactory,
                             PatientFactory patientFactory,
                             AdminFactory adminFactory) {

        this.userRepository = userRepository;
        this.veterinarianFactory = veterinarianFactory;
        this.patientFactory = patientFactory;
        this.adminFactory = adminFactory;
    }

    @Override
    public User createUser(RegistrationRequest registrationRequest) {

        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new UserAlreadyExistsException(String.format("Email already registered within the system - %s", registrationRequest.getEmail()));
        }

        switch (registrationRequest.getUserType()) {
            case "VET" -> {
                return veterinarianFactory.createVeterinarian(registrationRequest);
            }
            case "PATIENT" -> {
                return patientFactory.createPatient(registrationRequest);
            }
            case "ADMIN" -> {
                return adminFactory.createAdmin(registrationRequest);
            }
            default -> {
                return null;
            }
        }

    }
}
