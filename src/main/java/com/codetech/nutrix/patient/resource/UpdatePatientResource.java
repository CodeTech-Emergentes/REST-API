package com.codetech.nutrix.patient.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class UpdatePatientResource {

    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotNull
    @NotBlank
    @Size(min=8, max = 20) //check
    private String password;

    @NotNull
    @NotBlank
    @Size(min = 9, max = 9) //check
    private String phone;

    @NotNull
    @NotBlank
    private String date;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String gender;

    @NotNull
    @NotBlank
    @Size(max = 250)
    private String img;
}
