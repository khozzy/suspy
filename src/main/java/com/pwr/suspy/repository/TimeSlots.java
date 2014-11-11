package com.pwr.suspy.repository;

import com.pwr.suspy.domain.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlots extends JpaRepository<TimeSlot, Long> {

}
