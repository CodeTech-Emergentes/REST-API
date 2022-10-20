package com.codetech.nutrix.nutritionist.domain.persistence;

import com.codetech.nutrix.nutritionist.domain.model.entity.Nutritionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NutritionistRepository extends JpaRepository<Nutritionist, Long> {
    Nutritionist findByEmail(String email);
    List<Nutritionist> findByGenre(String genre);
    List<Nutritionist> findBySessionType(String sessionType);
    Nutritionist findByName(String name);
    List<Nutritionist> findByGenreAndSessionType(String genre, String sessionType);
}

