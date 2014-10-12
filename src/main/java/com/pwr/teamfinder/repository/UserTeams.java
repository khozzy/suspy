package com.pwr.teamfinder.repository;

import com.pwr.teamfinder.domain.UserTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTeams extends JpaRepository<UserTeam, Long> {

}
