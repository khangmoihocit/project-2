package com.javabykhang.project2pf.repository.custom;

import com.javabykhang.project2pf.builder.BuildingSeachBuilder;
import com.javabykhang.project2pf.repository.entity.BuildingEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> findAll(BuildingSeachBuilder buildingSeachBuilder);
}
