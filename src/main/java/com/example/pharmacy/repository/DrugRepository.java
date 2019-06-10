package com.example.pharmacy.repository;

import com.example.pharmacy.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug,Long> {
}
