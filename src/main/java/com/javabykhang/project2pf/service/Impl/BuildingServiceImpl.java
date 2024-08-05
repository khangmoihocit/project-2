package com.javabykhang.project2pf.service.Impl;
import com.javabykhang.project2pf.builder.BuildingSeachBuilder;
import com.javabykhang.project2pf.converter.BuildingDTOConverter;
import com.javabykhang.project2pf.converter.BuildingSeachBuilderConverter;
import com.javabykhang.project2pf.model.BuildingDTO;
import com.javabykhang.project2pf.repository.BuildingRepository;
import com.javabykhang.project2pf.repository.entity.BuildingEntity;
import com.javabykhang.project2pf.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingDTOConverter buildingDTOConverter;

    @Autowired
    private BuildingSeachBuilderConverter buildingSeachBuilderConverter;

    @Override
    public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typecode) {
        BuildingSeachBuilder buildingSeachBuilder = buildingSeachBuilderConverter.toBuildingSeachBuilder(params, typecode);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSeachBuilder);
        List<BuildingDTO> buildingDTOS = new ArrayList<>();
        for (BuildingEntity item : buildingEntities) {
            buildingDTOS.add(buildingDTOConverter.toBuildingDTO(item));
        }
        return buildingDTOS;
    }
}
