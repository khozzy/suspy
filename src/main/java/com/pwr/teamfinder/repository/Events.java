package com.pwr.teamfinder.repository;

import com.pwr.teamfinder.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Events extends JpaRepository<Event, Long> {

}
