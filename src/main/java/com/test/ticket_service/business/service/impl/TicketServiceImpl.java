package com.test.ticket_service.business.service.impl;

import com.test.ticket_service.business.service.TicketService;
import com.test.ticket_service.data.TicketRepo;
import com.test.ticket_service.data.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepo ticketRepo;

    @Autowired
    public TicketServiceImpl(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    @Override
    public Ticket create(Ticket data) {
        return ticketRepo.save(data);
    }

    @Override
    public Optional<Ticket> readById(Long id) {
        return ticketRepo.findById(id);
    }

    @Override
    public Page<Ticket> readAll(Pageable pageable) {
        return ticketRepo.findAll(pageable);
    }

    @Override
    public void update(Long id, Ticket newData) {
        // TODO
    }

    @Override
    public void delete(Long id) {
        // TODO
    }

    @Override
    public List<Ticket> readAllNew() {
        List<Ticket> tickets = ticketRepo.findAll();

        tickets = tickets.stream().filter((ticket) -> ticket.getPayment() != null).filter((ticket) ->
                ticket.getPayment().getPaymentStatus().equals("New")).collect(Collectors.toList());
        return tickets;
    }
}
