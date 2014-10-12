package com.pwr.teamfinder.repository;

import com.pwr.teamfinder.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Friends extends JpaRepository<Friend, Long> {

}
