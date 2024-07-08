package com.javabykhang.project2pf.repository.impl;

import com.javabykhang.project2pf.repository.RentareaRepository;
import com.javabykhang.project2pf.repository.entity.RentareaEntity;
import com.javabykhang.project2pf.utils.ConnectionJDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RentareaRepositoryImpl implements RentareaRepository {

    @Override
    public List<RentareaEntity> findValueByBuildingID(int buildingid) {
        String sql = "select r.value from rentarea r where r.buildingid =" + buildingid + " ";
        List<RentareaEntity> rentareaEntities = new ArrayList<>();
        try {
            Connection connection = ConnectionJDBCUtil.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                RentareaEntity rentareaEntity = new RentareaEntity();
                String value = String.valueOf(rs.getInt("value"));
                rentareaEntity.setValue(value);
                rentareaEntities.add(rentareaEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentareaEntities;
    }
}
