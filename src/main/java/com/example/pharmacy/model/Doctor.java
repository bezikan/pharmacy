package com.example.pharmacy.model;
import com.example.pharmacy.validator.ContactNumberConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String firstName;
    private String lastName;
    @ContactNumberConstraint
    private String phone;
    private User user;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor")
    @JsonIgnore
    private List<Patient> patients;
    @OneToMany(cascade = CascadeType.ALL,
    fetch = FetchType.LAZY,
    mappedBy = "doctor")
    @JsonIgnore
    private List<Meetings> meetings;

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

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Meetings> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meetings> meetings) {
        this.meetings = meetings;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
