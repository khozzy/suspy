package com.pwr.teamfinder.repository;

import com.pwr.teamfinder.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Teams extends JpaRepository<Team, Long> {

}
