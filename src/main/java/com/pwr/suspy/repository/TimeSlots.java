package com.pwr.suspy.repository;

import com.pwr.suspy.domain.Place;
import com.pwr.suspy.domain.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSlots extends JpaRepository<TimeSlot, Long>
{
    TimeSlot findById(long id);
    List<TimeSlot> findByPlace(@Param("place") Place place);// TEST IT
}
