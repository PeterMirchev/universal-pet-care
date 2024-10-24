package com.dailycodework.universalpetcare.service.appointment;

import com.dailycodework.universalpetcare.model.Appointment;
import com.dailycodework.universalpetcare.request.AppointmentRequest;

import java.util.List;

public interface IAppointmentService {

    Appointment createAppointment(Appointment appointment, Long sender, Long recipient);

    List<Appointment> getAllAppointments();

    Appointment updateAppointment(Long id, AppointmentRequest request);

    void deleteAppointment(Long id);

    Appointment findAppointmentById(Long id);

    Appointment findAppointmentByAppointmentNo(String appointmentNo);
}
