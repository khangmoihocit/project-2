package com.javabykhang.project2pf.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSeachBuilder {
    private String name;
    private String street;
    private String ward;
    private int numberOfBasement;
    private String managerName;
    private String managerPhoneNumber;
    private String districtCode;
    private List<String> typeCode = new ArrayList<>();
    private Long rentPriceFrom;
    private Long rentPriceTo;
    private Long areaFrom;
    private Long areaTo;
    private Long staffId;

    private BuildingSeachBuilder(Builder builder){
        this.name = builder.name;
        this.street = builder.street;
        this.ward = builder.ward;
        this.numberOfBasement = builder.numberOfBasement;
        this.managerName = builder.managerName;
        this.managerPhoneNumber = builder.managerPhoneNumber;
        this.districtCode = builder.districtCode;
        this.typeCode = builder.typeCode;
        this.rentPriceFrom = builder.rentPriceFrom;
        this.rentPriceTo = builder.rentPriceTo;
        this.areaFrom = builder.areaFrom;
        this.areaTo = builder.areaTo;
        this.staffId = builder.staffId;
    }
    public String getName() {
        return name;
    }
    public String getStreet() {
        return street;
    }
    public String getWard() {
        return ward;
    }

    public int getNumberOfBasement() {
        return numberOfBasement;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerPhoneNumber() {
        return managerPhoneNumber;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public List<String> getTypeCode() {
        return typeCode;
    }

    public Long getRentPriceFrom() {
        return rentPriceFrom;
    }

    public Long getRentPriceTo() {
        return rentPriceTo;
    }

    public Long getAreaFrom() {
        return areaFrom;
    }

    public Long getAreaTo() {
        return areaTo;
    }

    public Long getStaffId() {
        return staffId;
    }

    public static class Builder{
        private String name;
        private String street;
        private String ward;
        private int numberOfBasement;
        private String managerName;
        private String managerPhoneNumber;
        private String districtCode;
        private List<String> typeCode = new ArrayList<>();
        private Long rentPriceFrom;
        private Long rentPriceTo;
        private Long areaFrom;
        private Long areaTo;
        private Long staffId;

        public void setStaffId(Long staffId) {
            this.staffId = staffId;
        }

        public void setAreaTo(Long areaTo) {
            this.areaTo = areaTo;
        }

        public void setRentPriceTo(Long rentPriceTo) {
            this.rentPriceTo = rentPriceTo;
        }

        public void setAreaFrom(Long areaFrom) {
            this.areaFrom = areaFrom;
        }

        public void setRentPriceFrom(Long rentPriceFrom) {
            this.rentPriceFrom = rentPriceFrom;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public void setWard(String ward) {
            this.ward = ward;
        }

        public void setManagerName(String managerName) {
            this.managerName = managerName;
        }

        public void setDistrictCode(String districtCode) {
            this.districtCode = districtCode;
        }

        public void setTypeCode(List<String> typeCode) {
            this.typeCode = typeCode;
        }

        public void setManagerPhoneNumber(String managerPhoneNumber) {
            this.managerPhoneNumber = managerPhoneNumber;
        }

        public void setNumberOfBasement(int numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
        }

        public void setName(String name) {
            this.name = name;
        }
        public BuildingSeachBuilder build(){
            return new BuildingSeachBuilder(this);
        }
    }
}
