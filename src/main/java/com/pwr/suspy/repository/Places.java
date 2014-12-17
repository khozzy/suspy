package com.pwr.suspy.repository;

import com.pwr.suspy.domain.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Places extends JpaRepository<Place, Long> {

    Place findById(long id);

    @Query("SELECT p FROM Place p WHERE UPPER(p.name) LIKE UPPER(:name)")
    List<Place> findByNameContaining(@Param("name") String name);

    //Page<Place> findByNameAndAddressCityAndAddressStreetAndOwnerNameContaining(String query, Pageable pageable);
    Page<Place> findByNameContaining(String query, Pageable pageable);

}
