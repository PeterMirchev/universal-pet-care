package com.dailycodework.universalpetcare.service.appointment;

import com.dailycodework.universalpetcare.enums.AppointmentStatus;
import com.dailycodework.universalpetcare.exception.ResourceNotFoundException;
import com.dailycodework.universalpetcare.model.Appointment;
import com.dailycodework.universalpetcare.model.User;
import com.dailycodework.universalpetcare.repository.AppointmentRepository;
import com.dailycodework.universalpetcare.repository.UserRepository;
import com.dailycodework.universalpetcare.request.AppointmentRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements IAppointmentService{

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Appointment createAppointment(Appointment appointment, Long senderId, Long recipientId) {

        Optional<User> sender = userRepository.findById(senderId);
        Optional<User> recipient = userRepository.findById(senderId);

        appointment.setPatient(sender.get());
        appointment.setRecipient(recipient.get());
        appointment.setAppointmentNo();
        appointment.setStatus(AppointmentStatus.WAITING_FOR_APPROVAL);

        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {

        return appointmentRepository.findAll();
    }

    @Override
    public Appointment updateAppointment(Long id, AppointmentRequest request) {
        return null;
    }

    @Override
    public void deleteAppointment(Long id) {

        appointmentRepository
                .findById(id)
                .ifPresent(appointmentRepository::delete);
    }

    @Override
    public Appointment findAppointmentById(Long id) {

        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Appointment not found wit id - %s", id)));
    }

    @Override
    public Appointment findAppointmentByAppointmentNo(String appointmentNo) {
        return appointmentRepository.findByAppointmentNo(appointmentNo);
    }
}
