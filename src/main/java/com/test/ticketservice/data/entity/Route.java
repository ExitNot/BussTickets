package com.test.ticketservice.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "from", nullable = false)
    private Route from;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "to", nullable = false)
    private Route to;

    @Column(name = "departure", nullable = false)
    private Timestamp departure;

    @Column(name = "price")
    private Double price;

    @Column(name = "avail_cnt")
    private Integer avail_cnt;

    public Route() {
    }

    public Route(Route from, Route to, Timestamp departure, Double price, Integer avail_cnt) {
        this.from = from;
        this.to = to;
        this.departure = departure;
        this.price = price;
        this.avail_cnt = avail_cnt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Route getFrom() {
        return from;
    }

    public void setFrom(Route from) {
        this.from = from;
    }

    public Route getTo() {
        return to;
    }

    public void setTo(Route to) {
        this.to = to;
    }

    public Timestamp getDeparture() {
        return departure;
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

    public Integer getAvail_cnt() {
        return avail_cnt;
    }

    public void setAvail_cnt(Integer avail_cnt) {
        this.avail_cnt = avail_cnt;
    }
}
