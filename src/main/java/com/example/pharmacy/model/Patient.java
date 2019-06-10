package com.example.pharmacy.model;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
public class Patient {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;
    private String firstName;
    private String lastName;
    private User user;
    @Email
    private String email;
    @ManyToMany
    @JoinTable(
            name="patient_diseases",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id"))
    private List<Disease> diseases;
    private String deathRisk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Doctor doctor;

    @OneToOne(mappedBy = "patient")
    private Meetings meetings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    public String getDeathRisk() {
        return deathRisk;
    }

    public void setDeathRisk(String deathRisk) {
        this.deathRisk = deathRisk;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Meetings getMeetings() {
        return meetings;
    }

    public void setMeetings(Meetings meetings) {
        this.meetings = meetings;
    }
}
