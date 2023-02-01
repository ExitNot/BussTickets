package com.test.ticket_service.rest.controller;

import com.test.ticket_service.business.EntityNotFoundException;
import com.test.ticket_service.business.service.PaymentService;
import com.test.ticket_service.business.service.RouteService;
import com.test.ticket_service.business.service.TicketService;
import com.test.ticket_service.business.service.UserService;
import com.test.ticket_service.data.entity.Payment;
import com.test.ticket_service.data.entity.Route;
import com.test.ticket_service.data.entity.Ticket;
import com.test.ticket_service.data.entity.User;
import com.test.ticket_service.rest.dto.TicketDto;
import com.test.ticket_service.rest.dto.assembler.TicketDtoAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;


/* Ticket Management System */
@RestController
public class TicketController {

    private final PaymentService paymentService;
    private final RouteService routeService;
    private final UserService userService;
    private final TicketService ticketService;
    private final TicketDtoAssembler ticketDtoAssembler;
    private final PagedResourcesAssembler<Ticket> pagedResourcesAssembler;

    @Autowired
    public TicketController(PaymentService paymentService, RouteService routeService, UserService userService, TicketService ticketService, TicketDtoAssembler ticketDtoAssembler, PagedResourcesAssembler<Ticket> pagedResourcesAssembler) {
        this.routeService = routeService;
        this.paymentService = paymentService;
        this.userService = userService;
        this.ticketService = ticketService;
        this.ticketDtoAssembler = ticketDtoAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping("/ticket")
    public TicketDto readOne(@RequestParam("id") Long id) {
        return ticketDtoAssembler.toModel(
                ticketService.readById(Long.valueOf(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        ).add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).readAll(0, 5)
        ).withRel(IanaLinkRelations.COLLECTION));
    }

    @GetMapping("/tickets")
    public PagedModel<TicketDto> readAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
//        return pagedResourcesAssembler.toModel(ticketService.readAll(PageRequest.of(page, size)), ticketDtoAssembler);
    }

    @PostMapping("/buy-ticket")
    public Long buyTicket(@RequestBody Map<String, String> body) {
        Ticket ticket = null;
        User user = null;
        Optional<Route> oRoute = routeService.readById(Long.valueOf(body.get("routeId")));
        Optional<User> oUser = userService.readByFields(body.get("name"), body.get("surname"), body.get("middlename"));

        if (oRoute.isEmpty())
            throw new EntityNotFoundException("Undefined route");
        user = oUser.orElseGet(() -> userService.create(new User(body.get("name"), body.get("surname"), body.get("middlename"))));

        Payment payment = paymentService.create(new Payment(user, oRoute.get().getPrice(), "New"));
        ticket = ticketService.create(new Ticket(oRoute.get(), user, payment));
        routeService.decreaseAvailAmount(oRoute.get().getId());
        return ticket.getId();
    }
}
