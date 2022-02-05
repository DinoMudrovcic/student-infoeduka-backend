package com.dinomudrovcic.uniapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Student extends InfoedukaUser {

    private int ects;

    private int gradeAverage;


}
