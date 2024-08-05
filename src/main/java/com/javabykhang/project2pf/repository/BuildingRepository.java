package com.javabykhang.project2pf.repository;

import com.javabykhang.project2pf.builder.BuildingSeachBuilder;
import com.javabykhang.project2pf.repository.custom.BuildingRepositoryCustom;
import com.javabykhang.project2pf.repository.entity.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {
//    List<BuildingEntity> findAll(BuildingSeachBuilder buildingSeachBuilder);
    void deleteByIdIn(Long[] ids); //xóa nhiều id cùng lúc
    List<BuildingEntity> findByNameContaining(String s); //tim tên giống như like %b%
    List<BuildingEntity> findByNameContainingAndStreet(String name, String street); //tìm tên theo %% và street
}
