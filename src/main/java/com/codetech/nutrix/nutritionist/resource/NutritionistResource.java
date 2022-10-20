package com.codetech.nutrix.nutritionist.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NutritionistResource {
    private Long id;
    private String name;
    private String dni;
    private Date birthdayDate;
    private String email;
    private String password;
    private String phone;
    private String specialization;
    private String formation;
    private String about;
    private String genre;
    private String sessionType;
    private String image;
    private String cmp;
    private Boolean active;
    private Boolean fresh;

}
