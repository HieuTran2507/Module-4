package com.example.session6.service;

import com.example.session6.model.entity.Doctor;
import com.example.session6.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository dr;

    public Doctor addDoctor(Doctor d){
        dr.save(d);
        return d;
    }

    public boolean deleteDoctor(Long id){
        Doctor d = dr.findById(id).orElse(null);
        if (d == null) return false;
        else {
            dr.deleteById(id);
            return true;
        }
    }

    public List<Doctor> getAllDoctors(){
        return dr.findAll();
    }

    public Boolean updateDoctor(Long id, Doctor d2){
        Doctor d1 = dr.findById(id).orElse(null);
        if (d1 == null) return false;
        else {
            d1.setDoctorCode(d2.getDoctorCode());
            d1.setFullname(d2.getFullname());
            d1.setSpecialization(d2.getSpecialization());
            d1.setExperienceYears(d2.getExperienceYears());
            dr.save(d1);
            return true;
        }
    }
}
