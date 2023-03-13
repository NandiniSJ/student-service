package com.student.studentservice.service;

import com.student.studentservice.entity.Student;
import com.student.studentservice.model.request.StudentRequest;
import com.student.studentservice.model.response.StudentResponse;
import com.student.studentservice.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private final StudentRepository studentRepository = Mockito.mock(StudentRepository.class);

    private StudentService studentService;

    @BeforeEach
    void setUp(){
        studentService = new StudentService(studentRepository);
    }


    @Test
    void shouldSaveStudent(){
        Student student = new Student(null, "Nandini", "Jadhav", 3.45, 2020);
        StudentRequest studentRequest = new StudentRequest("Nandini", "Jadhav", 3.45, 2020);
        Mockito.when(studentRepository.saveAndFlush(Mockito.any())).thenReturn(student);

        StudentResponse saveStudent = studentService.save(studentRequest);

        assertEquals("Nandini", saveStudent.getFirstName());
        assertEquals(3.45, saveStudent.getGPA());
    }

    @Test
    void shouldReturnStudent(){
        Student student = new Student(1L, "Nandini", "Jadhav", 3.45, 2020);
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        StudentResponse studentResponse = studentService.get(1L);

        assertEquals("Nandini", studentResponse.getFirstName());
        assertEquals(1L, studentResponse.getId());

    }

    @Test
    void shouldDeleteStudent(){
        studentService.delete(1L);

        Mockito.verify(studentRepository).deleteById(1L);
    }


}