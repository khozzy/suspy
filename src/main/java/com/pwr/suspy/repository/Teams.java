package com.pwr.suspy.repository;

import com.pwr.suspy.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Teams extends JpaRepository<Team, Long> {

}
