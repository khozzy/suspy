package com.pwr.suspy.repository;

import com.pwr.suspy.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Events extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE UPPER(e.name) LIKE UPPER(:name)")
    List<Event> findByNameContaining(@Param("name") String name);
}
