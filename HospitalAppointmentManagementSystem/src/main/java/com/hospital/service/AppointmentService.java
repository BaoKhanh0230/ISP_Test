package com.hospital.service;

import com.hospital.model.Appointment;
import com.hospital.model.Doctor;
import com.hospital.model.Specialty;
import com.hospital.model.Patient;
import com.hospital.repository.AppointmentRepository;
import com.hospital.repository.DoctorRepository;
import com.hospital.repository.SpecialtyRepository;
import com.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public List<Appointment> getAllAppointmentsByPatient(int patientId) {
        return appointmentRepository.findByPatientPatientId(patientId);
    }

    public List<Specialty> getAllSpecialties() {
        return specialtyRepository.findAll();
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> getDoctorsBySpecialty(int specialtyId) {
        return doctorRepository.findBySpecialtySpecialtyId(specialtyId);
    }

    public List<Doctor> searchDoctors(String query) {
        return getAllDoctors().stream()
                .filter(d -> d.getDoctorName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void saveAppointment(Appointment appointment) {
        // Set the patient entity
        Patient patient = patientRepository.findById(appointment.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        appointment.setPatient(patient);

        // Set the specialty entity
        Specialty specialty = specialtyRepository.findById(appointment.getSpecialtyId())
                .orElseThrow(() -> new RuntimeException("Specialty not found"));
        appointment.setSpecialty(specialty);

        // Set the doctor entity if specified
        if (appointment.getDoctorId() != null) {
            Doctor doctor = doctorRepository.findById(appointment.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));
            appointment.setDoctor(doctor);
        }

        appointment.setCreatedDate(new Date());
        appointment.setStatus("Pending");
        appointmentRepository.save(appointment);
    }

    public Appointment getAppointmentById(int id) {
        return appointmentRepository.findById(id).orElse(null);
    }
}