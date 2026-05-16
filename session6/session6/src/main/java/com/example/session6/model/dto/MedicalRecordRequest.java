package com.example.session6.model.dto;

public class MedicalRecordRequest {
    private long doctorID;
    private long patientID;
    private String diagnosis;

    public MedicalRecordRequest() {
    }

    public MedicalRecordRequest(long doctorID, long patientID, String diagnosis) {
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.diagnosis = diagnosis;
    }

    public long getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(long doctorID) {
        this.doctorID = doctorID;
    }

    public long getPatientID() {
        return patientID;
    }

    public void setPatientID(long patientID) {
        this.patientID = patientID;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
