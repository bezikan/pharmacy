package com.example.pharmacy.dao;

import com.example.pharmacy.model.*;
import com.example.pharmacy.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.*;

@Configuration
@Slf4j
public class LoadDatabase {

    Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    public CommandLineRunner initDatabase(UserRepository userRepository, RoleRepository roleRepository, DiseaseRepository diseaseRepository, DrugRepository drugRepository, PatientRepository patientRepository, DoctorRepository doctorRepository, MeetingsRepository meetingsRepository) {
        return args -> {

            Drug d1 = new Drug();
            d1.setNameDrug("AB");
            drugRepository.save(d1);

            Disease disease = new Disease();
            disease.setDiseaseName("A");
            List<Drug> list = new LinkedList<>();
            list.add(d1);
            disease.setDrugs(list);
            diseaseRepository.save(disease);

            Patient p1 = new Patient();
            p1.setFirstName("Ewa");
            List<Disease> list1 = new LinkedList<>();
            list1.add(disease);
            p1.setDiseases(list1);
            patientRepository.save(p1);

            Doctor dr1 = new Doctor();
            dr1.setLastName("Nowak");
            doctorRepository.save(dr1);
            p1.setDoctor(dr1);



            Meetings m1 = new Meetings();
            m1.setDate(LocalDate.parse("2222-02-22"));
            m1.setDoctor(dr1);
            m1.setPatient(p1);
            meetingsRepository.save(m1);

            /*
            Role admin = new Role();
            admin.setRoleName("ADMIN");
            roleRepository.save(admin);

            User u1 = new User();
            u1.setUsername("admin");
            u1.setPassword("admin");
            Set<Role> list =new HashSet<>();
            list.add(admin);
            u1.setRoles(list);
            userRepository.save(u1);
            logger.info("saved!!!!!!!!!");



            Doctor dr2 = new Doctor();
            dr2.setLastName("Kowalski");
            dr2.setPhone("123456789");
            dr2.setUser(u1);
            doctorRepository.save(dr2);


            Doctor dr3 = new Doctor();
            dr3.setLastName("Markowski");
            dr3.setPhone("123456789");
            doctorRepository.save(dr3);
            */

        };
    }

}
