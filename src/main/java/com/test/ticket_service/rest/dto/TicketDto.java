package com.test.ticket_service.rest.dto;

import com.test.ticket_service.data.entity.Payment;
import com.test.ticket_service.data.entity.Route;
import com.test.ticket_service.data.entity.Ticket;
import com.test.ticket_service.data.entity.User;
import org.springframework.hateoas.RepresentationModel;

public class TicketDto extends RepresentationModel<TicketDto> implements Dto<Ticket> {

    private Long id;
    private Route route;
    private User user;
    private Payment payment;

    public TicketDto(Long id, Route route, User user, Payment payment) {
        this.id = id;
        this.route = route;
        this.user = user;
        this.payment = payment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public Ticket createEntity() {
        return new Ticket(route, user, payment);
    }
}
