package com.test.ticket_service.rest.dto;

import com.test.ticket_service.data.entity.Route;
import com.test.ticket_service.data.entity.Station;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class RouteDto extends RepresentationModel<RouteDto> implements Dto<Route> {

    private Long id;
    private Station from;
    private Station to;
    private Timestamp departure;
    private Double price;
    private Integer availCnt;

    public RouteDto(Long id, Station from, Station to, Timestamp departure, Double price, Integer availCnt) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.departure = departure;
        this.price = price;
        this.availCnt = availCnt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Station getFrom() {
        return from;
    }

    public void setFrom(Station from) {
        this.from = from;
    }

    public Station getTo() {
        return to;
    }

    public void setTo(Station to) {
        this.to = to;
    }

    public Timestamp getDeparture() {
        return departure;
    }

    public String getFormatDeparture() {
        return new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(departure);
    }

    public void setDeparture(Timestamp departure) {
        this.departure = departure;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAvailCnt() {
        return availCnt;
    }

    public void setAvailCnt(Integer availCnt) {
        this.availCnt = availCnt;
    }

    @Override
    public Route createEntity() {
        return new Route(from, to, departure, price, availCnt);
    }
}
