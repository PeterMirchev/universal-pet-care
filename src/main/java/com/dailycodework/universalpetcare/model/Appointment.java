package com.dailycodework.universalpetcare.model;

import com.dailycodework.universalpetcare.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reason;
    private LocalDate date;
    private LocalTime time;
    private String appointmentNo;
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @JoinColumn(name = "sender")
    @ManyToOne(fetch = FetchType.LAZY)
    private User patient;

    @JoinColumn(name = "recipient")
    @ManyToOne(fetch = FetchType.LAZY)
    private User recipient;


    public void addPatient(User sender) {

        this.setPatient(sender);

        if(sender.getAppointments() == null) {
            sender.setAppointments(new ArrayList<Appointment>());
        }

        sender.getAppointments().add(this);
    }

    public void addVeterinarian(User recipient) {

        this.setPatient(recipient);

        if(recipient.getAppointments() == null) {
            recipient.setAppointments(new ArrayList<Appointment>());
        }

        recipient.getAppointments().add(this);
    }

    public void setAppointmentNo() {

        this.appointmentNo = String.valueOf(new Random().nextLong()).substring(1, 11);
    }
}
