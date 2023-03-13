package com.student.studentservice.service;

import com.student.studentservice.entity.Student;
import com.student.studentservice.model.request.StudentRequest;
import com.student.studentservice.model.response.StudentResponse;
import com.student.studentservice.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentResponse save(StudentRequest studentRequest) {
        Student student = new Student(null, studentRequest.getFirstName(), studentRequest.getLastName(), studentRequest.getGpa(), studentRequest.getYearOfPassing());
        Student savedStudent = studentRepository.saveAndFlush(student);
        return new StudentResponse(savedStudent.getId(), savedStudent.getFirstName(), savedStudent.getLastname(), savedStudent.getGpa(), savedStudent.getYearOfPassing());
    }

    public StudentResponse get(Long id){
        final Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isEmpty()){
            throw new RuntimeException("Student not found for id:" + id);
        }
        final Student student = studentOptional.get();
        return new StudentResponse(student.getId(), student.getFirstName(),student.getLastname(),student.getGpa(),student.getYearOfPassing());
    }

    public void delete(Long id){
        studentRepository.deleteById(id);
    }

}
