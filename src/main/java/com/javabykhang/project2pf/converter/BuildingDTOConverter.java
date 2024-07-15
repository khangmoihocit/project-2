package com.javabykhang.project2pf.converter;

import com.javabykhang.project2pf.model.BuildingDTO;
import com.javabykhang.project2pf.repository.DistrictRepository;
import com.javabykhang.project2pf.repository.RentareaRepository;
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
    private RentareaRepository rentareaRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BuildingDTO toBuildingDTO(BuildingEntity entity){
        BuildingDTO buildingDTO = modelMapper.map(entity, BuildingDTO.class);

        buildingDTO.setAddress(entity.getStreet() + ", " + entity.getWard() + ", "
                + districtRepository.findNameById(entity.getDistrict().getId()).getName());

        List<RentareaEntity> rentareaEntities = rentareaRepository.findValueByBuildingID(entity.getId());
        String areaResult = rentareaEntities.stream().map(it -> it.getValue()).collect(Collectors.joining(","));
        buildingDTO.setRentArea(areaResult);

        return buildingDTO;
    }

}
