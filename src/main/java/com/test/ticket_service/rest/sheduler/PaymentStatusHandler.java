package com.test.ticket_service.rest.sheduler;

import com.test.ticket_service.business.service.RouteService;
import com.test.ticket_service.business.service.TicketService;
import com.test.ticket_service.data.entity.Ticket;
import com.test.ticket_service.rest.controller.PaymentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentStatusHandler {

    private final TicketService ticketService;
    private final RouteService routeService;

    @Autowired
    private PaymentController controller;

    @Autowired
    public PaymentStatusHandler(TicketService ticketService,
                                RouteService routeService) {
        this.ticketService = ticketService;
        this.routeService = routeService;
    }

    @Scheduled(fixedRate = 5000)
    public void checkPaymentStatus() {
        List<Ticket> tickets = ticketService.readAllNew();
        for (Ticket t : tickets) {
            String status = controller.statusReceiver(t.getPayment().getId());
            if (status.equals("Failed"))
                routeService.increaseAvailAmount(t.getRoute().getId());
        }
    }
}
