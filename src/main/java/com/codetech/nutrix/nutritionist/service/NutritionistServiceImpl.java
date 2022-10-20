package com.codetech.nutrix.nutritionist.service;

import com.codetech.nutrix.nutritionist.domain.model.entity.Nutritionist;
import com.codetech.nutrix.nutritionist.domain.persistence.NutritionistRepository;
import com.codetech.nutrix.nutritionist.domain.service.NutritionistService;
import com.codetech.nutrix.shared.exception.ResourceNotFoundException;
import com.codetech.nutrix.shared.exception.ResourceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class NutritionistServiceImpl implements NutritionistService {

    private final static String ENTITY = "Nutritionist";

    @Autowired
    private NutritionistRepository nutritionistRepository;

    @Autowired
    private Validator validator;


    @Override
    public List<Nutritionist> getAll() {
        return nutritionistRepository.findAll();
    }

    @Override
    public Nutritionist getById(Long nutritionistId) {
        return nutritionistRepository.findById(nutritionistId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, nutritionistId));
    }

    @Override
    public Nutritionist getByEmail(String nutritionistEmail) {
        return nutritionistRepository.findByEmail(nutritionistEmail);
    }

    @Override
    public List<Nutritionist> getByGenre(String nutritionistGenre) {
        return nutritionistRepository.findByGenre(nutritionistGenre);
    }

    @Override
    public List<Nutritionist> getBySessionType(String sessionType) {
        return nutritionistRepository.findBySessionType(sessionType);
    }

    @Override
    public List<Nutritionist> getByGenreAndSessionType(String genre, String sessionType) {
        return nutritionistRepository.findByGenreAndSessionType(genre, sessionType);
    }

    @Override
    public Nutritionist getByName(String name) {
        return nutritionistRepository.findByName(name);
    }

    @Override
    public Nutritionist create(Nutritionist request) {
        Set<ConstraintViolation<Nutritionist>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return nutritionistRepository.save(request);
    }

    @Override
    public Nutritionist update(Long nutritionistId, Nutritionist request) {
        Set<ConstraintViolation<Nutritionist>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return nutritionistRepository.findById(nutritionistId).map(nutritionist ->
                nutritionistRepository.save(
                        nutritionist.withName(request.getName())
                                .withDni(request.getDni())
                                .withBirthdayDate(request.getBirthdayDate())
                                .withEmail(request.getEmail())
                                .withPassword(request.getPassword())
                                .withPhone(request.getPhone())
                                .withSpecialization(request.getSpecialization())
                                .withFormation(request.getFormation())
                                .withAbout(request.getAbout())
                                .withGenre(request.getGenre())
                                .withSessionType(request.getSessionType())
                                .withImage(request.getImage())
                                .withCmp(request.getCmp())
                                .withActive(request.getActive())
                                .withFresh(request.getFresh())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, nutritionistId));
    }

    @Override
    public ResponseEntity<?> delete(Long nutritionistId) {

        return nutritionistRepository.findById(nutritionistId).map(nutritionist -> {
            nutritionistRepository.delete(nutritionist);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, nutritionistId));
    }
}
