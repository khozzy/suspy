package com.pwr.teamfinder.repository;

import com.pwr.teamfinder.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Gyms extends JpaRepository<Place, Long> {

}
