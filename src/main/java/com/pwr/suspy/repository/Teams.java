package com.pwr.suspy.repository;

import com.pwr.suspy.domain.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Teams extends JpaRepository<Team, Long> {

    Team findById(long id);
    
    Page<Team> findByNameContaining(String query, Pageable pageable);

}
