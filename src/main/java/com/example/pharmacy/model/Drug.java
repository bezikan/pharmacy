package com.example.pharmacy.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameDrug;
    private String description;
    private Long maxDoseMgPerDay;
    @ManyToMany(mappedBy = "drugs")
    private List<Disease> diseases;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameDrug() {
        return nameDrug;
    }

    public void setNameDrug(String nameDrug) {
        this.nameDrug = nameDrug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMaxDoseMgPerDay() {
        return maxDoseMgPerDay;
    }

    public void setMaxDoseMgPerDay(Long maxDoseMgPerDay) {
        this.maxDoseMgPerDay = maxDoseMgPerDay;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }
}
