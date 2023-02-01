package com.test.ticket_service.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "s_from", nullable = false)
    private Station from;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "s_to", nullable = false)
    private Station to;

    @Column(name = "departure", nullable = false)
    private Timestamp departure;

    @Column(name = "price")
    private Double price;

    @Column(name = "avail_cnt")
    private Integer avail_cnt;

    public Route() {
    }

    public Route(Station from, Station to, Timestamp departure, Double price, Integer avail_cnt) {
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
