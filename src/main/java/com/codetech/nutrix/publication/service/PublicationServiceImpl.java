package com.codetech.nutrix.publication.service;

import com.codetech.nutrix.nutritionist.domain.model.entity.Nutritionist;
import com.codetech.nutrix.nutritionist.domain.persistence.NutritionistRepository;
import com.codetech.nutrix.publication.domain.persistence.PublicationRepository;
import com.codetech.nutrix.publication.domain.model.entity.Publication;
import com.codetech.nutrix.publication.domain.service.PublicationService;
import com.codetech.nutrix.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationService {

    private final static String ENTITY = "Publication";

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private NutritionistRepository nutritionistRepository;

    @Override
    public List<Publication> getAll() {
        return publicationRepository.findAll();
    }

    //@Override
    //public Page<Publication> getAll(Pageable pageable) {
    //    return publicationRepository.findAll(pageable);
    //}

    @Override
    public Publication getById(Long publicationId) {
        return publicationRepository.findById(publicationId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, publicationId));
    }

    @Override
    public List<Publication> getByNutritionistId(Long nutritionistId) {
        return publicationRepository.findByNutritionistId(nutritionistId);
    }

    @Override
    public Publication create(Publication publication, Long nutritionistId) {
        Nutritionist nutritionist = nutritionistRepository.findById(nutritionistId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with Id " + nutritionistId));
        publication.setNutritionist(nutritionist);
        return publicationRepository.save(publication);
    }

    @Override
    public Publication update(Long publicationId, Publication request) {
        return publicationRepository.findById(publicationId).map(publication ->
                        publicationRepository.save(publication
                                .withTitle(request.getTitle())
                                .withDescription(request.getDescription())
                                .withTags(request.getTags())
                                .withContent(request.getContent())
                                .withPhotoUrl(request.getPhotoUrl())))
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, publicationId));
    }

    @Override
    public ResponseEntity<?> delete(Long publicationId) {
        return publicationRepository.findById(publicationId).map(publication -> {
            publicationRepository.delete(publication);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY, publicationId));
    }
}
