package com.javabykhang.project2pf.repository.entity;

public class BuildingEntity {
        private int id;
        private String name;
        private String street;
        private String ward;
        private int numberOfBasement;
        private int floorArea;
        private int rentPrice;
        private String managerName;
        private String managerPhoneNumber;
        private int districtId;
        private String rentpricedescription;

        public String getRentpricedescription() {
            return rentpricedescription;
        }

        public void setRentpricedescription(String rentpricedescription) {
            this.rentpricedescription = rentpricedescription;
        }

        public int getDistrictId() {
            return districtId;
        }

        public void setDistrictId(int districtId) {
            this.districtId = districtId;
        }

        public int getId() {
            return id;
        }


        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getWard() {
            return ward;
        }

        public void setWard(String ward) {
            this.ward = ward;
        }

        public int getNumberOfBasement() {
            return numberOfBasement;
        }

        public void setNumberOfBasement(int numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
        }

        public int getFloorArea() {
            return floorArea;
        }

        public void setFloorArea(int floorArea) {
            this.floorArea = floorArea;
        }

        public int getRentPrice() {
            return rentPrice;
        }

        public void setRentPrice(int rentPrice) {
            this.rentPrice = rentPrice;
        }

        public String getManagerName() {
            return managerName;
        }

        public void setManagerName(String managerName) {
            this.managerName = managerName;
        }

        public String getManagerPhoneNumber() {
            return managerPhoneNumber;
        }

        public void setManagerPhoneNumber(String managerPhoneNumber) {
            this.managerPhoneNumber = managerPhoneNumber;
        }
    }

