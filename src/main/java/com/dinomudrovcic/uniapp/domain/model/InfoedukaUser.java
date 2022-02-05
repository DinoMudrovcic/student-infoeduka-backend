package com.dinomudrovcic.uniapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class InfoedukaUser {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String phone;

}
