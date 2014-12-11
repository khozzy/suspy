package com.pwr.suspy.repository;

import com.pwr.suspy.domain.Address;
import com.pwr.suspy.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Places extends JpaRepository<Place, Long> {

    Place findById(long id);
    Place findByName(String name);
}
