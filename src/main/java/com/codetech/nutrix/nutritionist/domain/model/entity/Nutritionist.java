package com.codetech.nutrix.nutritionist.domain.model.entity;

import com.codetech.nutrix.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "nutritionist")
public class Nutritionist extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 40)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 9)
    private String dni;

    @NotNull
    private Date birthdayDate;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(min=8, max = 20)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 9)
    private String phone;

    @NotNull
    @NotBlank
    @Size(max=250)
    private String specialization;

    @NotNull
    @NotBlank
    @Size(max=250)
    private String formation;

    @NotNull
    @NotBlank
    @Size(max=250)
    private String about;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String genre;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String sessionType;

    @NotNull
    @NotBlank
    @Size(max=250)
    private String image;

    @NotNull
    @NotBlank
    @Size(max = 8)
    private String cmp;

    @NotNull
    private Boolean active;

    @NotNull
    private Boolean fresh;

    @OneToMany(mappedBy = "nutritionist")
    private List<NutritionistSchedule> nutritionistSchedules;

    public Nutritionist(Long id, String name, String dni, Date birthdayDate, String email, String password, String phone, String specialization, String formation, String about, String genre, String sessionType, String image, String cmp, Boolean active, Boolean fresh) {
        this.id = id;
        this.name = name;
        this.dni = dni;
        this.birthdayDate = birthdayDate;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.specialization = specialization;
        this.formation = formation;
        this.about = about;
        this.genre = genre;
        this.sessionType = sessionType;
        this.image = image;
        this.cmp = cmp;
        this.active = active;
        this.fresh = fresh;
    }
}
