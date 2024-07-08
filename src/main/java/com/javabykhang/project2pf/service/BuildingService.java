package com.javabykhang.project2pf.service;

import com.javabykhang.project2pf.model.BuildingDTO;

import java.util.List;
import java.util.Map;

public interface BuildingService {
    List<BuildingDTO> findAll(Map<String, Object> params, List<String> typecode);
}
