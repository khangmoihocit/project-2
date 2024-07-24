package com.javabykhang.project2pf.controller;

import com.javabykhang.project2pf.model.BuildingDTO;
import com.javabykhang.project2pf.model.BuildingRequestDTO;
import com.javabykhang.project2pf.repository.entity.BuildingEntity;
import com.javabykhang.project2pf.repository.entity.DistrictEntity;
import com.javabykhang.project2pf.service.BuildingService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@Transactional
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/get")
    public List<BuildingDTO> getBuilding(@RequestParam Map<String, Object> params,
                                         @RequestParam(name = "typecode", required = false) List<String> typecode){
        return buildingService.findAll(params, typecode);
    }

    @PostMapping("/get")
    public void createBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO){
        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity.setName(buildingRequestDTO.getName());
        buildingEntity.setStreet(buildingRequestDTO.getStreet());
        buildingEntity.setWard(buildingRequestDTO.getWard());
//        DistrictEntity districtEntity = new DistrictEntity();
//        districtEntity.setId(buildingRequestDTO.getDistrictId());
//        buildingEntity.setDistrict(districtEntity);
        //persist: tạo
        entityManager.persist(buildingEntity);
    }

    @PutMapping("/get")
    public void updateBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO){
        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity.setId(5L);
        buildingEntity.setName(buildingRequestDTO.getName());
        buildingEntity.setStreet(buildingRequestDTO.getStreet());
        buildingEntity.setWard(buildingRequestDTO.getWard());
//        DistrictEntity districtEntity = new DistrictEntity();
//        districtEntity.setId(buildingRequestDTO.getDistrictId());
//        buildingEntity.setDistrict(districtEntity);
        //merge: cập nhật
        entityManager.merge(buildingEntity);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBuilding(@PathVariable Long id){
        BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, id);
        entityManager.remove(buildingEntity);
    }

}
