package com.example.session6.model.entity;

import jakarta.persistence.*;

import javax.print.Doc;
import java.time.LocalDate;

@Entity
@Table(name = "medical_records")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diagnosis;

    public enum Status{
        processing,
        done
    }

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate createdAt;

    public MedicalRecord() {
    }

    public MedicalRecord(String diagnosis, Status status, Doctor doctor, Patient patient) {
        this.diagnosis = diagnosis;
        this.status = status;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersit(){
        if (status == null) status = Status.processing;
        if (createdAt == null) createdAt = LocalDate.now();
    }

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
