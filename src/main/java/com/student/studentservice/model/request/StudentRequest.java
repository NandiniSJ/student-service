package com.student.studentservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

    private String firstName;
    private String lastName;
    private double gpa;
    private int yearOfPassing;

}
