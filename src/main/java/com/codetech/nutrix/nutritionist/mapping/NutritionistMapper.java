package com.codetech.nutrix.nutritionist.mapping;

import com.codetech.nutrix.nutritionist.resource.CreateNutritionistResource;
import com.codetech.nutrix.nutritionist.resource.NutritionistResource;
import com.codetech.nutrix.nutritionist.resource.UpdateNutritionistResource;
import com.codetech.nutrix.nutritionist.domain.model.entity.Nutritionist;
import com.codetech.nutrix.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class NutritionistMapper {

    @Autowired
    private EnhancedModelMapper mapper;

    // Object Mapping

    public Page<NutritionistResource> modelListToPage(List<Nutritionist> modelList, Pageable pageable) {
        return new PageImpl<>(
                mapper.mapList(modelList, NutritionistResource.class),
                pageable,
                modelList.size());
    }

    public NutritionistResource toResource(Nutritionist model) {
        return mapper.map(model, NutritionistResource.class);
    }

    public List<NutritionistResource> toResource(List<Nutritionist> model) {
        return mapper.mapList(model, NutritionistResource.class);
    }

    public Nutritionist toModel(CreateNutritionistResource resource) {
        return mapper.map(resource, Nutritionist.class);
    }

    public Nutritionist toModel(UpdateNutritionistResource resource) {
        return mapper.map(resource, Nutritionist.class);
    }
}
