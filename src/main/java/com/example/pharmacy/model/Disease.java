package com.example.pharmacy.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String diseaseName;
    @ManyToMany
    @JoinTable(
            name = "disease_drugs",
            joinColumns = @JoinColumn(name="disease_id"),
            inverseJoinColumns = @JoinColumn(name="drug_id")
    )
    private List<Drug> drugs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }
}
