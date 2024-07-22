package com.javabykhang.project2pf.repository.impl;

import com.javabykhang.project2pf.builder.BuildingSeachBuilder;
import com.javabykhang.project2pf.repository.BuildingRepository;
import com.javabykhang.project2pf.repository.entity.BuildingEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findAll(BuildingSeachBuilder buildingSeachBuilder) {
        //JPQL(JPA Query Language)
        String sql = "FROM BuildingEntity";
        Query query = entityManager.createQuery(sql, BuildingEntity.class);
        return query.getResultList();
    }
}
