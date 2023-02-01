package com.test.ticket_service.business.service;

import com.test.ticket_service.business.CrudService;
import com.test.ticket_service.data.entity.Ticket;

import java.util.List;

public interface TicketService extends CrudService<Ticket, Long> {

    List<Ticket> readAllNew();
}
