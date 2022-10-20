package com.codetech.nutrix.nutritionist.api;

import com.codetech.nutrix.nutritionist.domain.model.entity.Nutritionist;
import com.codetech.nutrix.nutritionist.domain.model.entity.NutritionistSchedule;
import com.codetech.nutrix.nutritionist.domain.model.entity.NutritionistScheduleFK;
import com.codetech.nutrix.nutritionist.domain.model.entity.Schedule;
import com.codetech.nutrix.nutritionist.domain.persistence.NutritionistScheduleRepository;
import com.codetech.nutrix.nutritionist.domain.persistence.ScheduleRepository;
import com.codetech.nutrix.nutritionist.domain.service.NutritionistService;
import com.codetech.nutrix.nutritionist.domain.service.ScheduleService;
import com.codetech.nutrix.nutritionist.mapping.NutritionistMapper;
import com.codetech.nutrix.nutritionist.mapping.ScheduleMapper;
import com.codetech.nutrix.nutritionist.resource.CreateNutritionistResource;
import com.codetech.nutrix.nutritionist.resource.NutritionistResource;
import com.codetech.nutrix.nutritionist.resource.ScheduleResource;
import com.codetech.nutrix.nutritionist.resource.UpdateNutritionistResource;
import com.codetech.nutrix.shared.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "nutritionist")
@RestController
@RequestMapping("/api/v1/nutritionists")
@CrossOrigin
public class NutritionistController {

    @Autowired
    private NutritionistService nutritionistService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private NutritionistScheduleRepository nutritionistScheduleRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private NutritionistMapper mapper;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Operation(summary = "Get nutritionists", description = "Get All nutritionists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "nutritionists found"),
            @ApiResponse(responseCode = "400",description = "nutritionist not found")})
    @GetMapping
    public List<NutritionistResource> getAllNutritionists() {
        return mapper.toResource(nutritionistService.getAll());
    }

    @Operation(summary = "Get nutritionists by Id", description = "Get nutritionist by Id")
    @GetMapping("{nutritionistId}")
    public NutritionistResource getById(@PathVariable Long nutritionistId)
    {
        return mapper.toResource(nutritionistService.getById(nutritionistId));
    }

    @Operation(summary = "Get nutritionists by Id", description = "Get nutritionist by Id")
    @GetMapping("{nutritionistId}/schedules")
    public List<ScheduleResource> getSchedulesByNutritionistId(@PathVariable Long nutritionistId)
    {
        return scheduleMapper.toResource(scheduleRepository.findByNutritionistId(nutritionistId));
    }

    @Operation(summary = "Get schedule by Id", description = "Get schedule by Id")
    @GetMapping("schedule/{scheduleId}")
    public ScheduleResource getScheduleById(@PathVariable Long scheduleId)
    {
        return scheduleMapper.toResource(scheduleService.getById(scheduleId));
    }

    @Operation(summary = "Get nutritionists by Email", description = "Get nutritionist by Email")
    @GetMapping("email/{nutritionistEmail}")
    public NutritionistResource getByEmail(@PathVariable String nutritionistEmail)
    {
        return mapper.toResource(nutritionistService.getByEmail(nutritionistEmail));
    }

    @Operation(summary = "Get nutritionists by Genre", description = "Get nutritionist by Genre")
    @GetMapping("genre/{psychoGenre}")
    public List<NutritionistResource> getNutritionistByGenre(@PathVariable String psychoGenre)
    {
        return mapper.toResource(nutritionistService.getByGenre(psychoGenre));
    }

    @Operation(summary = "Get nutritionists by session type", description = "Get nutritionist by session type")
    @GetMapping("sessionType/{sessionType}")
    public List<NutritionistResource> getNutritionistsBySessionType(@PathVariable String sessionType)
    {
        return mapper.toResource(nutritionistService.getBySessionType(sessionType));
    }

    @Operation(summary = "Get nutritionists by name", description = "Get nutritionist by name")
    @GetMapping("name/{name}")
    public NutritionistResource getNutritionistByName(@PathVariable String name)
    {
        return mapper.toResource(nutritionistService.getByName(name));
    }

    @Operation(summary = "Get nutritionists by genre and session type", description = "Get nutritionist by genre and session type")
    @GetMapping("genre/{genre}&sessionType/{sessionType}")
    public List<NutritionistResource> getNutritionistByGenreAndSessionType(@PathVariable String genre, @PathVariable String sessionType)
    {
        return mapper.toResource(nutritionistService.getByGenreAndSessionType(genre,sessionType));
    }

    @Operation(summary = "Create nutritionist", description = "Create nutritionist")
    @PostMapping
    public NutritionistResource createNutritionist(@RequestBody CreateNutritionistResource request)
    {
        return mapper.toResource(nutritionistService.create(mapper.toModel(request)));
    }

    @Operation(summary = "Update nutritionist", description = "Update nutritionist by Id")
    @PutMapping("{nutritionistId}")
    public NutritionistResource updateNutritionist(@PathVariable Long nutritionistId, @RequestBody UpdateNutritionistResource request)
    {
        return mapper.toResource(nutritionistService.update(nutritionistId, mapper.toModel(request)));
    }

    @Operation(summary = "Create schedule", description = "Create schedule")
    @PostMapping("{scheduleId}/{nutritionistId}")
    public NutritionistSchedule createSchedule(@PathVariable Long scheduleId, @PathVariable Long nutritionistId)
    {
        try {
            Nutritionist nutritionist = nutritionistService.getById(nutritionistId);
            Schedule schedule = scheduleService.getById(scheduleId);

            if (schedule == null || nutritionist == null) {
                throw new ResourceNotFoundException("Schedule or nutritionist not found");
            }
            NutritionistScheduleFK newFK = new NutritionistScheduleFK(nutritionistId, scheduleId);
            NutritionistSchedule nutritionistSchedule = new NutritionistSchedule(newFK, nutritionist, schedule);
            nutritionistScheduleRepository.save(nutritionistSchedule);
            return nutritionistSchedule;
        }catch (Exception e) {
            throw new ResourceNotFoundException("Schedule or nutritionist not found");
        }

        //nutritionist nutritionist = nutritionistService.getById(nutritionistId);
        //Schedule schedule = scheduleService.getById(scheduleId);

        //if(schedule == null || nutritionist == null)
        //{
        //    throw new ResourceNotFoundException("Schedule or nutritionist not found");
        //}
        //nutritionistScheduleFK newFK = new nutritionistScheduleFK(nutritionistId,scheduleId);
        //nutritionistSchedule nutritionistSchedule = new nutritionistSchedule(newFK,nutritionist,schedule);
        //nutritionistScheduleRepository.save(nutritionistSchedule);

        //return nutritionistSchedule;
    }

    @Operation(summary = "Delete nutritionist", description = "Delete nutritionist by Id")
    @DeleteMapping("{nutritionistId}")
    public ResponseEntity<?> deleteNutritionist(@PathVariable Long nutritionistId)
    {
        return nutritionistService.delete(nutritionistId);
    }


}
