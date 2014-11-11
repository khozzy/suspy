package com.pwr.suspy.service.generic;


import com.pwr.suspy.domain.BaseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Collection;

public interface GenericService<T extends BaseEntity, PK extends Serializable, R extends JpaRepository<T, PK>> {

    R getRepository();

    T save(T entity);

    void delete(PK id);

    void delete(T entity);

    Collection<T> findAll();

    T findById(PK id);

    boolean exists(PK id);

    Iterable<T> findAll(Sort sort);

    Collection<T> findAll(Pageable pageable);

}
