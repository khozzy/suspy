package com.pwr.teamfinder.service.generic;

import com.pwr.teamfinder.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Transactional
public abstract class GenericServiceImpl<T extends BaseEntity, PK extends Serializable, R extends JpaRepository<T, PK>> implements GenericService<T, PK, R> {

    public abstract R getRepository();

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public void delete(PK id) {
        T entity = findById(id);
        entity.setDeleted(true);
        entity.setDeletedDate(new Date());
        save(entity);
    }

    @Override
    public void delete(T entity) {
        entity.setDeleted(true);
        entity.setDeletedDate(new Date());
        save(entity);
    }

    @Override
    public T findById(PK id) {
        return getRepository().findOne(id);
    }

    @Override
    public Collection<T> getAll() {
        return getRepository()
                .findAll()
                .stream()
                .collect(Collectors.toList());
    }
}