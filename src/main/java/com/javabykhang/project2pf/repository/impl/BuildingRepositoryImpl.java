package com.javabykhang.project2pf.repository.impl;

import com.javabykhang.project2pf.builder.BuildingSeachBuilder;
import com.javabykhang.project2pf.repository.BuildingRepository;
import com.javabykhang.project2pf.repository.entity.BuildingEntity;
import com.javabykhang.project2pf.utils.ConnectionJDBCUtil;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

    public static void joinTable(BuildingSeachBuilder buildingSeachBuilder, StringBuilder sql){
        Long staffid = buildingSeachBuilder.getStaffId();
        if (staffid != null){
            sql.append("inner join assignmentbuilding on b.id = assignmentbuilding.buildingid ");
        }
        List<String> typecode = buildingSeachBuilder.getTypeCode();
        if (typecode != null && typecode.size() != 0){
            sql.append("inner join buildingrenttype on b.id = buildingrenttype.buildingid ");
            sql.append("inner join renttype on renttype.id = buildingrenttype.renttypeid ");
        }
        Long rentAreaTo = buildingSeachBuilder.getAreaTo();
        Long rentAreaFrom = buildingSeachBuilder.getAreaFrom();
        if (rentAreaTo != null || rentAreaFrom != null){
            sql.append("inner join rentarea on b.id = rentarea.buildingid ");
        }


    }
    public static void queryNormal(BuildingSeachBuilder buildingSeachBuilder, StringBuilder where) {
//        for (Map.Entry<String, Object> it : params.entrySet()){
//            if (!it.getKey().equals("staffid") && !it.getKey().equals("typecode")
//                    &&!it.getKey().startsWith("area") &&!it.getKey().startsWith("rentPrice")){
//                String value = it.getValue().toString();
//                if(StringUtil.checkString(value) == true){
//                    if(NumberUtil.isNumber(value) == true){   //neu la so
//                        where.append(" and b." + it.getKey() + " = " + value + " ");
//                    }
//                    else{ //la xau
//                        where.append(" and b." + it.getKey() + " like '%" + value + "%' ");
//                    }
//                }
//            }
//        }

        //java reflection: dùng để duyệt các field của đối tượng
        try {
            Field[] fields = BuildingSeachBuilder.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if (!fieldName.equals("staffid") && !fieldName.equals("typecode")
                        && !fieldName.startsWith("area") && !fieldName.startsWith("rentPrice")) {
                    Object value = item.get(buildingSeachBuilder);
                    if (value != null) {
                            if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")) {   //neu la so
                            where.append(" and b." + fieldName + " = " + value + " ");
                        } else if(item.getType().getName().equals("java.lang.String")) { //la xau
                            where.append(" and b." + fieldName + " like '%" + value + "%' ");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void querySpecial(BuildingSeachBuilder buildingSeachBuilder, StringBuilder where){
        Long staffid = buildingSeachBuilder.getStaffId();
        if(staffid != null){
            where.append(" and assignmentbuilding.staffid = " + staffid + " ");
        }
        Long rentAreaTo = buildingSeachBuilder.getAreaTo();
        Long rentAreaFrom = buildingSeachBuilder.getAreaFrom();
        if (rentAreaTo != null || rentAreaFrom != null){
            if(rentAreaFrom != null){
                where.append(" and rentarea.value <= " + rentAreaFrom);
            }
            if(rentAreaTo != null){
                where.append(" and rentarea.value >= " + rentAreaTo);
            }
        }
        Integer rentPriceTo = buildingSeachBuilder.getRentPriceTo();
        Integer rentPriceFrom = buildingSeachBuilder.getRentPriceFrom();
        if (rentPriceTo != null || rentPriceFrom != null){
            if(rentPriceTo != null){
                where.append(" and rentprice >= " + rentPriceTo);
            }
            if(rentPriceFrom != null){
                where.append(" and rentprice <= " + rentPriceFrom);
            }
        }
        //java 7
//        if (typecode != null && typecode.size() != 0){
//            List<String> code = new ArrayList<>();
//            for (String item : typecode){
//                code.add("'" + item + "'");
//            }
//            where.append(" and renttype.code in (" + String.join(",", code) + ") ");
//        }

        //java 8
        List<String> typecode = buildingSeachBuilder.getTypeCode();
        if (typecode != null && typecode.size() != 0){
            where.append(" and (");
            String sql = typecode.stream().map(it -> "renttype.code like '%" + it + "%'").collect(Collectors.joining(" or "));
            where.append(sql + ")");
        }
    }

    @Override
    public List<BuildingEntity> findAll(BuildingSeachBuilder buildingSeachBuilder) {
        StringBuilder sql = new StringBuilder("select b.id, b.name, b.street, b.ward, b.numberofbasement, " +
                "b.floorarea, b.rentprice, b.managername, b.managerphonenumber " +
                ", b.districtid, b.servicefee, b.brokeragefee from building b ");
        joinTable(buildingSeachBuilder, sql);
        StringBuilder where = new StringBuilder("where 1=1 ");
        queryNormal(buildingSeachBuilder, where);
        querySpecial(buildingSeachBuilder, where);
        where.append(" group by b.id");
        sql.append(where);
        List<BuildingEntity> buildingEntities = new ArrayList<>();
        try {
            Connection connection = ConnectionJDBCUtil.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql.toString());
            while(rs.next()){
                BuildingEntity buildingEntity = new BuildingEntity();
                buildingEntity.setId(rs.getInt("id"));
                buildingEntity.setName(rs.getString("name"));
                buildingEntity.setStreet(rs.getString("street"));
                buildingEntity.setWard(rs.getString("ward"));
                buildingEntity.setNumberOfBasement(rs.getInt("numberofbasement"));
                buildingEntity.setFloorArea(rs.getInt("floorarea"));
                buildingEntity.setRentPrice(rs.getInt("rentprice"));
                buildingEntity.setManagerPhoneNumber(rs.getString("managerphonenumber"));
                buildingEntity.setManagerName(rs.getString("managername"));
                buildingEntity.setDistrictId(rs.getInt("districtid"));
                buildingEntities.add(buildingEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buildingEntities;
    }
}

