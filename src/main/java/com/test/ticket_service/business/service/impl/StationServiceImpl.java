package com.test.ticket_service.business.service.impl;

import com.test.ticket_service.business.service.StationService;
import com.test.ticket_service.data.StationRepo;
import com.test.ticket_service.data.entity.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StationServiceImpl implements StationService {

    private final StationRepo stationRepo;

    @Autowired
    public StationServiceImpl(StationRepo stationRepo) {
        this.stationRepo = stationRepo;
    }

    @Override
    public Station create(Station data) {
        return null;
    }

    @Override
    public Optional<Station> readById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<Station> readAll(Pageable pageable) {
        return null;
    }

    @Override
    public void update(Long id, Station newData) {}

    @Override
    public void delete(Long id) {}
}
