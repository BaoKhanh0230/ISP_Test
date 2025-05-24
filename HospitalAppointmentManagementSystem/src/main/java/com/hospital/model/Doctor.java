package com.hospital.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctorId;

    private String doctorName;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    public Doctor() {}
    public Doctor(int doctorId, String doctorName, Specialty specialty) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialty = specialty;
    }

    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }
    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
    public Specialty getSpecialty() { return specialty; }
    public void setSpecialty(Specialty specialty) { this.specialty = specialty; }

    // Temporary method to maintain compatibility with existing code
    public int getSpecialtyId() {
        return specialty != null ? specialty.getSpecialtyId() : 0;
    }
    public void setSpecialtyId(int specialtyId) {
        if (this.specialty == null) {
            this.specialty = new Specialty();
        }
        this.specialty.setSpecialtyId(specialtyId);
    }
}