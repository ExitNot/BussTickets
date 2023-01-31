package com.test.ticketservice.data;

import com.test.ticketservice.data.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepo extends JpaRepository<Station, Long> {}
