package com.javabykhang.project2pf.controller;

import com.javabykhang.project2pf.model.BuildingDTO;
import com.javabykhang.project2pf.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

@RestController
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @GetMapping("/get")
    public List<BuildingDTO> getBuilding(@RequestParam Map<String, Object> params,
                                         @RequestParam(name = "typecode", required = false) List<String> typecode){
        return buildingService.findAll(params, typecode);
    }
}
