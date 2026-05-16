package com.example.session6.service;

import com.example.session6.model.dto.PaginationResponse;
import com.example.session6.model.entity.Patient;
import com.example.session6.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    private PatientRepository pr;

    public Patient addPatient(Patient p){
        pr.save(p);
        return p;
    }

    public Boolean deletePatient(Long id){
        Patient p = pr.findById(id).orElse(null);
        if (p==null) return false;
        else pr.deleteById(id);
        return true;
    }

    public Page<Patient> findAllAndSearch(Pageable pageable , String patientName){
        return pr.findByFullNameContaining(pageable,patientName);
    }

    public PaginationResponse searchPatient(String patientName, int page, int size){
        Pageable pageable = PageRequest.of(page,size, Sort.by("fullName").descending());
        Page<Patient> patientPage = findAllAndSearch(pageable,patientName);
        PaginationResponse response = new PaginationResponse(
                patientPage.getContent(), // List<Patient> data
                patientPage.getTotalPages(), // total page
                patientPage.getNumberOfElements(), // total element
                patientPage.getNumber() // current page
        );
        return response;
    }
}
