package com.student.studentservice.controller;

import com.student.studentservice.model.request.StudentRequest;
import com.student.studentservice.model.response.StudentResponse;
import com.student.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public StudentResponse save(@RequestBody StudentRequest studentRequest){
        return studentService.save(studentRequest);
    }

    @GetMapping("/student")
    public StudentResponse get(@RequestParam(value = "id") Long id){
        return studentService.get(id);
    }

    @DeleteMapping("/student")
    public void delete(@RequestParam(value = "id") Long id){
        studentService.delete(id);
    }
}
