package com.javabykhang.project2pf.repository.custom.impl;

import com.javabykhang.project2pf.builder.BuildingSeachBuilder;
import com.javabykhang.project2pf.repository.BuildingRepository;
import com.javabykhang.project2pf.repository.custom.BuildingRepositoryCustom;
import com.javabykhang.project2pf.repository.entity.BuildingEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    //JPA
    @PersistenceContext
    private EntityManager entityManager;

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
                where.append(" and rentarea.value >= " + rentAreaFrom);
            }
            if(rentAreaTo != null){
                where.append(" and rentarea.value <= " + rentAreaTo);
            }
        }
        Long rentPriceTo = buildingSeachBuilder.getRentPriceTo();
        Long rentPriceFrom = buildingSeachBuilder.getRentPriceFrom();
        if (rentPriceTo != null || rentPriceFrom != null){
            if(rentPriceTo != null){
                where.append(" and rentprice >= " + rentPriceTo);
            }
            if(rentPriceFrom != null){
                where.append(" and rentprice <= " + rentPriceFrom);
            }
        }

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
        StringBuilder sql = new StringBuilder("select b.* from building b ");
        joinTable(buildingSeachBuilder, sql);
        StringBuilder where = new StringBuilder("where 1=1 ");
        queryNormal(buildingSeachBuilder, where);
        querySpecial(buildingSeachBuilder, where);
        where.append(" group by b.id");
        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }
}

