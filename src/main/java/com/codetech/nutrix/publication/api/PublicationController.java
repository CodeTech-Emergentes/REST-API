package com.codetech.nutrix.publication.api;

import com.codetech.nutrix.publication.domain.service.PublicationService;
import com.codetech.nutrix.publication.mapping.PublicationMapper;
import com.codetech.nutrix.publication.resource.CreatePublicationResource;
import com.codetech.nutrix.publication.resource.PublicationResource;
import com.codetech.nutrix.publication.resource.UpdatePublicationResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Publication")
@RestController
@RequestMapping("/api/v1/publications")
@CrossOrigin
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private PublicationMapper mapper;


    @Operation(summary = "Get Publications", description = "Get All Publications")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Patients found"),
            @ApiResponse(responseCode = "400",description = "Patient not found") })
    @GetMapping
    public List<PublicationResource> getAllPublications(){
        return mapper.toResource(publicationService.getAll());
    }

    @Operation(summary = "Create Publication", description = "Create Publication")
    @PostMapping("nutritionists/{nutritionistId}")
    public PublicationResource createPublication(@PathVariable Long nutritionistId,  @Valid @RequestBody CreatePublicationResource request){
        return mapper.toResource(publicationService.create(mapper.toModel(request),nutritionistId));
    }

    @Operation(summary = "Get Publication by Id", description = "Get Publication by Id")
    @GetMapping("{publicationId}")
    public PublicationResource getPublicationById(@PathVariable Long publicationId){
        return mapper.toResource(publicationService.getById(publicationId));
    }

    @Operation(summary = "Update Publication", description = "Update Publication by Id ")
    @PutMapping("{publicationId}")
    public PublicationResource updatePublication(@PathVariable Long publicationId, @Valid @RequestBody UpdatePublicationResource request){
        return mapper.toResource(publicationService.update(publicationId,mapper.toModel(request)));
    }

    @Operation(summary = "Delete Publication", description = "Delete Publication by Id")
    @DeleteMapping("{publicationId}")
    public ResponseEntity<?> deletePublication(@PathVariable Long publicationId){
        return publicationService.delete(publicationId);
    }

    @Operation(summary = "Get Publication by nutritionist Id", description = "Get Publication by nutritionist Id")
    @GetMapping("nutritionist/{nutritionistId}")
    public List<PublicationResource> getPublicationByNutritionistId(@PathVariable Long nutritionistId){
        return mapper.toResource(publicationService.getByNutritionistId(nutritionistId));
    }











}
