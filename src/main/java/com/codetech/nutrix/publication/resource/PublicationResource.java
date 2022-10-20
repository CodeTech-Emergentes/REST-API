package com.codetech.nutrix.publication.resource;

import com.codetech.nutrix.nutritionist.resource.NutritionistResource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicationResource {
    private Long id;
    private String title;
    private String tags;
    private String description;
    private String photoUrl;
    private String content;
    private NutritionistResource nutritionist;
}
