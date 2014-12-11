package com.pwr.suspy.repository;

import com.pwr.suspy.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Events extends JpaRepository<Event, Long> {
    Page<Event> findByNameContaining(String name, Pageable pageable);
}
