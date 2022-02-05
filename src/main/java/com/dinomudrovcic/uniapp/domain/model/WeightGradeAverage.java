package com.dinomudrovcic.uniapp.domain.model;


public class WeightGradeAverage {

    Double calculateWeightGradeAverage(final int ects, final int avgGrade) {
        return Double.valueOf(avgGrade * ects / 2);
    }

}
