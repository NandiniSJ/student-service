package com.student.studentservice.controller;

import com.student.studentservice.model.request.StudentRequest;
import com.student.studentservice.model.response.StudentResponse;
import com.student.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public StudentResponse save(@RequestBody StudentRequest studentRequest){
        return studentService.save(studentRequest);
    }
}
