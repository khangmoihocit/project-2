package com.javabykhang.project2pf.repository.impl;

import com.javabykhang.project2pf.repository.BuildingRepository;
import com.javabykhang.project2pf.repository.entity.BuildingEntity;
import com.javabykhang.project2pf.utils.ConnectionJDBCUtil;
import com.javabykhang.project2pf.utils.NumberUtil;
import com.javabykhang.project2pf.utils.StringUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

    public static void joinTable(Map<String, Object> params, List<String> typecode, StringBuilder sql){
        String staffid = (String)params.get("staffid");
        if (StringUtil.checkString(staffid)){
            sql.append("inner join assignmentbuilding on b.id = assignmentbuilding.buildingid ");
        }
        if (typecode != null && typecode.size() != 0){
            sql.append("inner join buildingrenttype on b.id = buildingrenttype.buildingid ");
            sql.append("inner join renttype on renttype.id = buildingrenttype.renttypeid ");
        }
        String rentAreaTo = (String)params.get("areaTo");
        String rentAreaFrom = (String)params.get("areaFrom");
        if (StringUtil.checkString(rentAreaTo)|| StringUtil.checkString(rentAreaFrom)){
            sql.append("inner join rentarea on b.id = rentarea.buildingid ");
        }


    }
    public static void queryNormal(Map<String, Object> params, StringBuilder where){
        for (Map.Entry<String, Object> it : params.entrySet()){
            if (!it.getKey().equals("staffid") && !it.getKey().equals("typecode")
                    &&!it.getKey().startsWith("area") &&!it.getKey().startsWith("rentPrice")){
                String value = it.getValue().toString();
//                where.append(" and b." + it.getKey() + " like '%" + value + "%' ");
                if(StringUtil.checkString(value) == true){
                    if(NumberUtil.isNumber(value) == true){   //neu la so
                        where.append(" and b." + it.getKey() + " = " + value + " ");
                    }
                    else{ //la xau
                        where.append(" and b." + it.getKey() + " like '%" + value + "%' ");
                    }
                }
            }
        }
    }
    public static void querySpecial(Map<String, Object> params, List<String> typecode, StringBuilder where){
        String staffid = (String)params.get("staffid");
        if(StringUtil.checkString(staffid)){
            where.append(" and assignmentbuilding.staffid = " + staffid + " ");
        }
        String rentAreaTo = (String)params.get("areaTo");
        String rentAreaFrom = (String)params.get("areaFrom");
        if (StringUtil.checkString(rentAreaTo) == true || StringUtil.checkString(rentAreaFrom) == true){
            if(StringUtil.checkString(rentAreaFrom)){
                where.append(" and rentarea.value <= " + rentAreaFrom);
            }
            if(StringUtil.checkString(rentAreaTo)){
                where.append(" and rentarea.value >= " + rentAreaTo);
            }
        }
        String rentPriceTo = (String)params.get("rentPriceTo");
        String rentPriceFrom = (String)params.get("rentPriceFrom");
        if (StringUtil.checkString(rentPriceTo) == true || StringUtil.checkString(rentPriceFrom) == true){
            if(StringUtil.checkString(rentPriceTo)){
                where.append(" and rentprice.value >= " + rentPriceTo);
            }
            if(StringUtil.checkString(rentPriceFrom)){
                where.append(" and rentprice.value <= " + rentPriceFrom);
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
        if (typecode != null && typecode.size() != 0){
            where.append(" and (");
            String sql = typecode.stream().map(it -> "renttype.code like '%" + it + "%'").collect(Collectors.joining(" or "));
            where.append(sql + ")");
        }
    }

    @Override
    public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typecode) {
        StringBuilder sql = new StringBuilder("select b.id, b.name, b.street, b.ward, b.numberofbasement, " +
                "b.floorarea, b.rentprice, b.managername, b.managerphonenumber " +
                ", b.districtid, b.servicefee, b.brokeragefee from building b ");
        joinTable(params, typecode, sql);
        StringBuilder where = new StringBuilder("where 1=1 ");
        queryNormal(params, where);
        querySpecial(params, typecode, where);
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
