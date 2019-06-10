package com.example.pharmacy;

import com.example.pharmacy.model.Doctor;
import com.example.pharmacy.repository.DoctorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DoctorControllerMockTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateRetrieveMockMVC() throws Exception {
        this.mockMvc.perform(get("/doctors")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/doctors/2")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Nowak")));
    }

    /*
    @Autowired
    @MockBean
    private DoctorRepository doctorRepository;

    //@Test
    public void testRetrieceDoctorWithMockRepository() throws Exception {
        Doctor doctor1 = new Doctor();
        doctor1.setFirstName("Adam");
        doctor1.setLastName("Nowak");
        Optional<Doctor> optDoctor = Optional.of(doctor1);
        when(doctorRepository.findById(3L)).thenReturn(optDoctor);

        assertTrue(doctorRepository.findById(3L).get().getFirstName().contains("Adam"));

    }
*/

}
