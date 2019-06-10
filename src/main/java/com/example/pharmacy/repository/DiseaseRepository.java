package com.example.pharmacy.repository;

import com.example.pharmacy.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease,Long> {
}
