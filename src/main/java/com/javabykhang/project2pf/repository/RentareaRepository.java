package com.javabykhang.project2pf.repository;

import com.javabykhang.project2pf.repository.entity.RentareaEntity;

import java.util.List;

public interface RentareaRepository {
    List<RentareaEntity> findValueByBuildingID(Long id);
}
