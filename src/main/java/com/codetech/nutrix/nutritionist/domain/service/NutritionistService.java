package com.codetech.nutrix.nutritionist.domain.service;

import com.codetech.nutrix.nutritionist.domain.model.entity.Nutritionist;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NutritionistService {
    List<Nutritionist> getAll();
    Nutritionist getById(Long nutritionistId);
    Nutritionist create(Nutritionist request);
    Nutritionist update(Long nutritionistId, Nutritionist request);
    ResponseEntity<?> delete(Long nutritionistId);
    Nutritionist getByEmail(String email);
    List<Nutritionist>getByGenre(String genre);
    List<Nutritionist>getBySessionType(String sessionType);
    Nutritionist getByName(String name);
    List<Nutritionist> getByGenreAndSessionType(String genre, String sessionType);
}
