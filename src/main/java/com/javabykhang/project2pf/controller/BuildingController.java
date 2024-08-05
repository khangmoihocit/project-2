package com.javabykhang.project2pf.controller;

import com.javabykhang.project2pf.model.BuildingDTO;
import com.javabykhang.project2pf.model.BuildingRequestDTO;
import com.javabykhang.project2pf.repository.BuildingRepository;
import com.javabykhang.project2pf.repository.entity.BuildingEntity;
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

    //JPA of hibernate
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BuildingRepository buildingRepository;

    @GetMapping("/getAll")
    public List<BuildingDTO> getBuildings(@RequestParam Map<String, Object> params,
                                         @RequestParam(name = "typecode", required = false) List<String> typecode){
        return buildingService.findAll(params, typecode);
    }

//    @GetMapping("/get/{id}")
//    public BuildingEntity getBuilding(@PathVariable Long id){
//        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
//        return buildingEntity;
//    }

    @GetMapping("/get/{name}/{street}")
    public List<BuildingEntity> getBuildingByName(@PathVariable String name, @PathVariable String street){
        List<BuildingEntity> buildingEntity = buildingRepository.findByNameContainingAndStreet(name, street);
        return buildingEntity;
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

    @PutMapping("/update")
    public void updateBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO){
        BuildingEntity buildingEntity = buildingRepository.findById(buildingRequestDTO.getId()).get();
        buildingEntity.setName(buildingRequestDTO.getName());
        buildingEntity.setStreet(buildingRequestDTO.getStreet());
        buildingEntity.setWard(buildingRequestDTO.getWard());
//        DistrictEntity districtEntity = new DistrictEntity();
//        districtEntity.setId(buildingRequestDTO.getDistrictId());
//        buildingEntity.setDistrict(districtEntity);
        //merge: cập nhật
        buildingRepository.save(buildingEntity);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBuilding(@PathVariable Long[] id){
//        buildingRepository.deleteById(id);
        buildingRepository.deleteByIdIn(id);
    }

}
