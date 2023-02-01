package com.test.ticket_service.business.service.impl;

import com.test.ticket_service.business.service.RouteService;
import com.test.ticket_service.data.RouteRepo;
import com.test.ticket_service.data.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepo routeRepo;

    @Autowired
    public RouteServiceImpl(RouteRepo routeRepo) {
        this.routeRepo = routeRepo;
    }

    @Override
    public Route create(Route data) {
        return null;
    }

    @Override
    public Optional<Route> readById(Long id) {
        return routeRepo.findById(id);
    }

    @Override
    public Page<Route> readAll(Pageable pageable) {
        return routeRepo.findAll(pageable);
    }

    @Override
    public void update(Long id, Route newData) {}

    @Override
    public void delete(Long id) {}

    @Override
    public void decreaseAvailAmount(Long id) {
        Optional<Route> route = routeRepo.findById(id);
        if (route.isPresent()) {
            route.get().setAvail_cnt(route.get().getAvail_cnt() - 1);
            routeRepo.save(route.get());
        }
    }

    @Override
    public void increaseAvailAmount(Long id) {
        Optional<Route> route = routeRepo.findById(id);
        if (route.isPresent()) {
            route.get().setAvail_cnt(route.get().getAvail_cnt() + 1);
            routeRepo.save(route.get());
        }
    }
}
