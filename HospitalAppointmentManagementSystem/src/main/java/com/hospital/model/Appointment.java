package com.hospital.model;

import jakarta.persistence.*;
import java.sql.Time;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = true)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date appointmentDate;

    @DateTimeFormat(pattern = "HH:mm")
    private Time appointmentTime;

    private Date createdDate;
    private Date approvedDate;
    private String reason;
    private String status;

    public Appointment() {}
    public Appointment(int appointmentId, Patient patient, Doctor doctor, Specialty specialty, Date appointmentDate, 
                       Time appointmentTime, Date createdDate, Date approvedDate, String reason, String status) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.specialty = specialty;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.createdDate = createdDate;
        this.approvedDate = approvedDate;
        this.reason = reason;
        this.status = status;
    }

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public Specialty getSpecialty() { return specialty; }
    public void setSpecialty(Specialty specialty) { this.specialty = specialty; }
    public Date getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(Date appointmentDate) { this.appointmentDate = appointmentDate; }
    public Time getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(Time appointmentTime) { this.appointmentTime = appointmentTime; }
    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
    public Date getApprovedDate() { return approvedDate; }
    public void setApprovedDate(Date approvedDate) { this.approvedDate = approvedDate; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getPatientId() { return patient != null ? patient.getPatientId() : 0; }
    public void setPatientId(int patientId) {
        if (this.patient == null) {
            this.patient = new Patient();
        }
        this.patient.setPatientId(patientId);
    }
    public Integer getDoctorId() { return doctor != null ? doctor.getDoctorId() : null; }
    public void setDoctorId(Integer doctorId) {
        if (doctorId == null) {
            this.doctor = null;
        } else {
            if (this.doctor == null) {
                this.doctor = new Doctor();
            }
            this.doctor.setDoctorId(doctorId);
        }
    }
    public int getSpecialtyId() { return specialty != null ? specialty.getSpecialtyId() : 0; }
    public void setSpecialtyId(int specialtyId) {
        if (this.specialty == null) {
            this.specialty = new Specialty();
        }
        this.specialty.setSpecialtyId(specialtyId);
    }
}