package com.pwr.teamfinder.repository;

import com.pwr.teamfinder.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Users extends JpaRepository<User, Long> {

}
