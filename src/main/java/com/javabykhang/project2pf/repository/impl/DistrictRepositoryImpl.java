package com.javabykhang.project2pf.repository.impl;

import com.javabykhang.project2pf.repository.DistrictRepository;
import com.javabykhang.project2pf.repository.entity.BuildingEntity;
import com.javabykhang.project2pf.repository.entity.DistrictEntity;
import com.javabykhang.project2pf.utils.ConnectionJDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

    @Override
    public DistrictEntity findNameById(long id) {
        String sql = "select d.name from district d where d.id = " + id + " ";
        DistrictEntity districtEntity = new DistrictEntity();
        try {
            Connection connection = ConnectionJDBCUtil.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                districtEntity.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return districtEntity;
    }
}
