package com.codetech.nutrix.publication.domain.service;

import com.codetech.nutrix.publication.domain.model.entity.Publication;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PublicationService {
    List<Publication>getAll();
    //Page<Publication> getAll(Pageable pageable);
    Publication getById(Long publicationId);
    Publication create(Publication publication, Long nutritionistId);
    Publication update(Long publicationId, Publication request);
    ResponseEntity<?> delete(Long publicationId);
    List<Publication> getByNutritionistId(Long nutritionistId);
}
