package com.pwr.suspy.repository;

import com.pwr.suspy.domain.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Places extends JpaRepository<Place, Long> {

    Place findById(long id);
    Page<Place> findByNameContaining(String name, Pageable pageable);

}
