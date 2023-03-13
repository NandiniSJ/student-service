package com.student.studentservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private double GPA;
    private int yearOfPassing;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentResponse that = (StudentResponse) o;
        return Double.compare(that.GPA, GPA) == 0 && yearOfPassing == that.yearOfPassing && Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, GPA, yearOfPassing);
    }
}
