package com.codetech.nutrix.nutritionist.mapping;

import com.codetech.nutrix.nutritionist.resource.CreateScheduleResource;
import com.codetech.nutrix.nutritionist.resource.ScheduleResource;
import com.codetech.nutrix.nutritionist.resource.UpdateScheduleResource;
import com.codetech.nutrix.nutritionist.domain.model.entity.Schedule;
import com.codetech.nutrix.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ScheduleMapper {

    @Autowired
    private EnhancedModelMapper mapper;

    public ScheduleResource toResource(Schedule model)
    {
        return mapper.map(model, ScheduleResource.class);
    }

    public List<ScheduleResource> toResource(List<Schedule> model)
    {
        return mapper.mapList(model, ScheduleResource.class);
    }

    public Page<ScheduleResource> modelListToPage(List<Schedule> modelList, Pageable pageable)
    {
        return new PageImpl<>(
                mapper.mapList(modelList, ScheduleResource.class),
                pageable,
                modelList.size());
    }

    public Schedule toModel(CreateScheduleResource resource)
    {
        return mapper.map(resource, Schedule.class);
    }

    public Schedule toModel(UpdateScheduleResource resource)
    {
        return mapper.map(resource, Schedule.class);
    }
}
