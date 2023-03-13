package com.student.studentservice.controller;

import com.student.studentservice.entity.Student;
import com.student.studentservice.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldSaveStudent() throws Exception {

        String body = "{\n" +
                "   \"firstName\": \"Nandini\",\n" +
                "   \"lastName\": \"Jadhav\",\n" +
                "   \"gpa\": \"3.45\",\n" +
                "   \"yearOfPassing\": \"2020\"\n" +
                "}";
        this.mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());

        final List<Student> students = studentRepository.findAll();

        assertEquals("Nandini", students.get(0).getFirstName());

    }

    @Test
    void shouldReturnStudent() throws Exception {
        Student student = new Student(1L, "Nandini", "Jadhav", 3.45, 2020);
        studentRepository.save(student);

        this.mockMvc.perform(get("/student?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(student.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(student.getLastname())))
                .andExpect(jsonPath("$.gpa", is(student.getGpa())))
                .andExpect(jsonPath("$.yearOfPassing", is(student.getYearOfPassing())));
    }

    @Test
    void shouldDeleteStudent() throws Exception {
        Student student = new Student(1L, "Nandini", "Jadhav", 3.45, 2020);
        studentRepository.save(student);
        this.mockMvc.perform(delete("/student?id=1"))
                .andExpect(status().isOk());
        final Optional<Student> studentOptional = studentRepository.findById(1L);
        assertTrue(studentOptional.isEmpty());
    }

    @Test
    void shouldUpdateStudent() throws Exception {
        Student student = new Student(1L, "Nandini", "Jadhav", 3.45, 2020);
        studentRepository.save(student);

        String body = "{\n" +
                "   \"firstName\": \"Nandini\",\n" +
                "   \"lastName\": \"Jadhav\",\n" +
                "   \"gpa\": \"4.45\",\n" +
                "   \"yearOfPassing\": \"2020\"\n" +
                "}";

        this.mockMvc.perform(put("/student?id=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());

        Student updatedStudent = studentRepository.findById(1L).get();
        assertEquals(4.45, updatedStudent.getGpa());


    }


}