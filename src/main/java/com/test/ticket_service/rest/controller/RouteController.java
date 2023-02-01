package com.test.ticket_service.rest.controller;

import com.test.ticket_service.business.service.RouteService;
import com.test.ticket_service.data.entity.Route;
import com.test.ticket_service.rest.dto.RouteDto;
import com.test.ticket_service.rest.dto.assembler.RouteDtoAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RouteController {

    private final RouteService routeService;
    private final RouteDtoAssembler routeDtoAssembler;
    private final PagedResourcesAssembler<Route> pagedResourcesAssembler;

    @Autowired
    public RouteController(RouteService routeService, RouteDtoAssembler routeDtoAssembler, PagedResourcesAssembler<Route> pagedResourcesAssembler) {
        this.routeService = routeService;
        this.routeDtoAssembler = routeDtoAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping("/routes?id={id}")
    public RouteDto readOne(@PathVariable("id") Long id) {
        return routeDtoAssembler.toModel(
                routeService.readById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        ).add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).readAll(0, 5)
        ).withRel(IanaLinkRelations.COLLECTION));
    }

    @GetMapping("/routes")
    public PagedModel<RouteDto> readAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return pagedResourcesAssembler.toModel(routeService.readAll(PageRequest.of(page, size)), routeDtoAssembler);
    }
}
