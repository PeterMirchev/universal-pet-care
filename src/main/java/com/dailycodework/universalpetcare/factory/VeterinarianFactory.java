package com.dailycodework.universalpetcare.factory;

import com.dailycodework.universalpetcare.model.User;
import com.dailycodework.universalpetcare.model.Veterinarian;
import com.dailycodework.universalpetcare.repository.VeterinarianRepository;
import com.dailycodework.universalpetcare.request.RegistrationRequest;
import com.dailycodework.universalpetcare.service.user.UserAttributesMapper;
import org.springframework.stereotype.Service;

@Service
public class VeterinarianFactory {

    private final VeterinarianRepository veterinarianRepository;
    private final UserAttributesMapper userAttributesMapper;

    public VeterinarianFactory(VeterinarianRepository veterinarianRepository,
                               UserAttributesMapper userAttributesMapper) {

        this.veterinarianRepository = veterinarianRepository;
        this.userAttributesMapper = userAttributesMapper;
    }

    public User createVeterinarian(RegistrationRequest request) {

        Veterinarian veterinarian = new Veterinarian();
        userAttributesMapper.setCommonAttributes(request, veterinarian);
        veterinarian.setSpecialization(request.getSpecialization());

        return veterinarianRepository.save(veterinarian);
    }
}
