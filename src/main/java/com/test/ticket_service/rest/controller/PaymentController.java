package com.test.ticket_service.rest.controller;

import com.test.ticket_service.business.service.PaymentService;
import com.test.ticket_service.business.service.RouteService;
import com.test.ticket_service.business.service.TicketService;
import com.test.ticket_service.business.service.UserService;
import com.test.ticket_service.data.entity.Payment;
import com.test.ticket_service.data.entity.Ticket;
import com.test.ticket_service.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.Map;
import java.util.Optional;

@RestController
public class PaymentController {

    private final PaymentService paymentService;
    private final UserService userService;
    private final RouteService routeService;
    private final TicketService ticketService;
    private final PagedResourcesAssembler<Payment> pagedResourcesAssembler;

    @Autowired
    public PaymentController(PaymentService paymentService, UserService userService, RouteService routeService, PagedResourcesAssembler<Payment> pagedResourcesAssembler, TicketService ticketService) {
        this.paymentService = paymentService;
        this.userService = userService;
        this.routeService = routeService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.ticketService = ticketService;
    }

    @GetMapping("/payment")
    public Long payment(@RequestBody Map<String, String> body) {
        Ticket ticket = null;
        Optional<User> oUser = userService.readByFields(body.get("name"), body.get("surname"), body.get("middlename"));

        ticket = ticketService.readById(oUser.get().getId()).orElseThrow(EntityNotFoundException::new);

        return paymentService.readById(ticket.getPayment().getId())
                .orElseThrow(EntityNotFoundException::new).getId();
    }

    @GetMapping("/payment/status_receiver")
    public String statusReceiver(@RequestParam Long id) {
        Optional<Payment> oPayment = paymentService.readById(id);
        oPayment.orElseThrow(EntityNotFoundException::new);

        return oPayment.get().getPaymentStatus();
    }

//    @Scheduled(fixedRate = 5000)
//    public void checkPaymentStatus() {
//        List<Ticket> tickets = ticketService.readAllNew();
//
//        for (Ticket t : tickets) {
//            String status = statusReceiver(t.getPayment().getId());
//            System.out.println();
//            if (status.equals("Failed"))
//                routeService.increaseAvailAmount(t.getRoute().getId());
//        }
//    }
}
