package com.pwr.teamfinder.repository;

import com.pwr.teamfinder.domain.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlots extends JpaRepository<TimeSlot, Long> {

}
