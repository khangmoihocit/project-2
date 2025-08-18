package com.javabykhang.project2pf.converter;

import com.javabykhang.project2pf.model.BuildingDTO;
import com.javabykhang.project2pf.repository.entity.BuildingEntity;
import com.javabykhang.project2pf.repository.entity.RentareaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingDTO toBuildingDTO(BuildingEntity entity){
        BuildingDTO buildingDTO = modelMapper.map(entity, BuildingDTO.class);

        buildingDTO.setAddress(entity.getStreet() + ", " + entity.getWard() + ", "
                + entity.getDistrict().getName());

        List<RentareaEntity> rentareaEntities = entity.getRentareaEntities();
        String areaResult = rentareaEntities.stream().map(it -> it.getValue()).collect(Collectors.joining(","));
        buildingDTO.setRentArea(areaResult);

        return buildingDTO;
    }
}
