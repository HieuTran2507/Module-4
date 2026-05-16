package com.example.session6.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String doctorCode;
    private String fullname;
    private String specialization;
    private Integer experienceYears;

    public Doctor() {
    }

    public Doctor(Long id, String doctorCode, String fullname, String specialization, Integer experienceYears) {
        this.id = id;
        this.doctorCode = doctorCode;
        this.fullname = fullname;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
    }

    @OneToMany(mappedBy = "doctor")
    List<MedicalRecord> medicalRecords;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }
}
