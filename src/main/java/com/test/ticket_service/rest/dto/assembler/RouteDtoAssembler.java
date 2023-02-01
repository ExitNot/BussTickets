package com.test.ticket_service.rest.dto.assembler;

import com.sun.istack.NotNull;
import com.test.ticket_service.data.entity.Route;
import com.test.ticket_service.rest.controller.RouteController;
import com.test.ticket_service.rest.dto.RouteDto;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class RouteDtoAssembler extends RepresentationModelAssemblerSupport<Route, RouteDto> {

    public RouteDtoAssembler() {
        super(RouteController.class, RouteDto.class);
    }

    @Override
    public RouteDto toModel(@NotNull Route entity) {
        if (entity == null)
            return null;

        return new RouteDto(
                entity.getId(),
                entity.getFrom(),
                entity.getTo(),
                entity.getDeparture(),
                entity.getPrice(),
                entity.getAvail_cnt()
        ).add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RouteController.class).readOne(entity.getId())).withSelfRel());
    }
}
