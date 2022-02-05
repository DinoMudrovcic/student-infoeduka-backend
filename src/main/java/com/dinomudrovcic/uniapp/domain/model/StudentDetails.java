package com.dinomudrovcic.uniapp.domain.model;

public class StudentDetails {

    String getStudentFullName(final String name, final String surname) {
        return String.format("{} {}", name, surname);
    }

    String getStudentDetailedEcts(Student student) {
        return String.format("{} {} - ECTS : {}", student.getName(), student.getSurname(), student.getEcts());
    }

}
