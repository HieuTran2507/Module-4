package com.example.session6.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patientCode;
    private String fullName;
    private String phone;
    private String address;

    public Patient() {
    }

    public Patient(Long id, String patientCode, String fullName, String phone, String address) {
        this.id = id;
        this.patientCode = patientCode;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
    }

    @OneToMany(mappedBy = "patient")
    List<MedicalRecord> medicalRecords;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
