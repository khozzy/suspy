package com.pwr.teamfinder.repository;

import com.pwr.teamfinder.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Users extends JpaRepository<User, Long> {

}
