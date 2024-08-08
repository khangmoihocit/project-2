package com.javabykhang.project2pf.converter;

import com.javabykhang.project2pf.builder.BuildingSeachBuilder;
import com.javabykhang.project2pf.utils.MapUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component //đánh dấu đây là 1 class không có hàm khởi tạo, đối tượng bth ,bean
public class BuildingSeachBuilderConverter {

    //mục đích hứng dữ liệu từ client bằng builder patten thay vì sử dụng map
    public BuildingSeachBuilder toBuildingSeachBuilder(Map<String, Object> params, List<String> typecode){
        BuildingSeachBuilder buildingSeachBuilder = new BuildingSeachBuilder.Builder()
                                                            .setName(MapUtil.getObject(params, "name", String.class))
                                                            .setWard(MapUtil.getObject(params, "ward", String.class))
                                                            .setStreet(MapUtil.getObject(params, "street", String.class))
                                                            .setManagerName(MapUtil.getObject(params, "managerName", String.class))
                                                            .setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", Integer.class))
                                                            .setAreaFrom(MapUtil.getObject(params, "areaFrom", Long.class))
                                                            .setAreaTo(MapUtil.getObject(params, "areaTo", Long.class))
                                                            .setDistrictid(MapUtil.getObject(params, "districtid", String.class))
                                                            .setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", Long.class))
                                                            .setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", Long.class))
                                                            .setTypeCode(typecode)
                                                            .setStaffId(MapUtil.getObject(params, "staffId", Long.class))
                                                            .build();
        return buildingSeachBuilder;
    }
}
