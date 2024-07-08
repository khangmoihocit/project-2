package com.javabykhang.project2pf.repository;

import com.javabykhang.project2pf.repository.entity.DistrictEntity;

public interface DistrictRepository {
    DistrictEntity findNameById(long id);
}
