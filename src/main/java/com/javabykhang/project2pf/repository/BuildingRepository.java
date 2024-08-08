package com.javabykhang.project2pf.repository;

import com.javabykhang.project2pf.builder.BuildingSeachBuilder;
import com.javabykhang.project2pf.repository.entity.BuildingEntity;

import java.util.List;
import java.util.Map;

public interface BuildingRepository {
    List<BuildingEntity> findAll(BuildingSeachBuilder buildingSeachBuilder);
}
