package com.codetech.nutrix.publication.domain.model.entity;

import com.codetech.nutrix.nutritionist.domain.model.entity.Nutritionist;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.codetech.nutrix.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "publications")
public class Publication extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 150)
    private String title;

    @NotNull
    @NotBlank
    @Size(max = 250)
    private String description;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String tags;

    @NotNull
    @NotBlank
    private String photoUrl;
    @NotNull
    @NotBlank
    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nutritionist_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Nutritionist nutritionist;


    public Publication(Long id, String title, String description, String tags, String photoUrl, String content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.photoUrl = photoUrl;
        this.content = content;
    }
}
