package com.codetech.nutrix.appointment.mapping;

import com.codetech.nutrix.appointment.resource.AppointmentResource;
import com.codetech.nutrix.appointment.resource.CreateAppointmentResource;
import com.codetech.nutrix.appointment.resource.UpdateAppointmentResource;
import com.codetech.nutrix.appointment.domain.model.entity.Appointment;
import com.codetech.nutrix.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class AppointmentMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    // Object Mapping

    public AppointmentResource toResource(Appointment model) {
        return mapper.map(model, AppointmentResource.class);
    }

    public List<AppointmentResource> toResource(List<Appointment> model) {
        return mapper.mapList(model, AppointmentResource.class);
    }

    public Page<AppointmentResource> modelListToPage(List<Appointment> modelList, Pageable pageable) {
        return new PageImpl<>(
                mapper.mapList(modelList, AppointmentResource.class),
                pageable,
                modelList.size());
    }

    public Appointment toModel(CreateAppointmentResource resource) {
        return mapper.map(resource, Appointment.class);
    }

    public Appointment toModel(UpdateAppointmentResource resource) {
        return mapper.map(resource, Appointment.class);
    }
}
