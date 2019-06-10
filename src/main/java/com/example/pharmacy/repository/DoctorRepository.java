package com.example.pharmacy.repository;

import com.example.pharmacy.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("select d  from Doctor d")
    Collection<Doctor> allPhones();
}
