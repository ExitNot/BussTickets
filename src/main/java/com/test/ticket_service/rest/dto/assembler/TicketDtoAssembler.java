package com.test.ticket_service.rest.dto.assembler;

import com.test.ticket_service.data.entity.Ticket;
import com.test.ticket_service.rest.controller.TicketController;
import com.test.ticket_service.rest.dto.TicketDto;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class TicketDtoAssembler extends RepresentationModelAssemblerSupport<Ticket, TicketDto> {

    public TicketDtoAssembler() {
        super(TicketController.class, TicketDto.class);
    }

    @Override
    public TicketDto toModel(Ticket entity) {
        return new TicketDto(
                entity.getId(),
                entity.getRoute(),
                entity.getUser(),
                entity.getPayment()
        ).add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TicketController.class).readOne(entity.getId())).withSelfRel());
    }
}
