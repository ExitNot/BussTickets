package com.test.ticket_service.business.service;

import com.test.ticket_service.business.CrudService;
import com.test.ticket_service.data.entity.Route;

public interface RouteService extends CrudService<Route, Long> {

    void decreaseAvailAmount(Long id);

    void increaseAvailAmount(Long id);
}
