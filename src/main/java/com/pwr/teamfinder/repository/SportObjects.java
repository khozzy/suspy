package com.pwr.teamfinder.repository;

import com.pwr.teamfinder.domain.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportObjects extends JpaRepository<Gym, Long> {

}
