package com.javabykhang.project2pf.model;

public class BuildingDTO {
    private String name;
    private String address;
    private int numberOfBasement;
    private String rentArea;
    private String managerName;
    private String managerPhoneNumber;
    private int floorArea;
    private int dtTrong;
    private float rentPrice;
    private float phiMG;


    public String getRentArea() {
        return rentArea;
    }

    public void setRentArea(String rentArea) {
        this.rentArea = rentArea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(int numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
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

    public void setManagerPhoneNumber(String soDienThoai) {
        this.managerPhoneNumber = soDienThoai;
    }

    public int getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(int floorArea) {
        this.floorArea = floorArea;
    }

    public int getDtTrong() {
        return dtTrong;
    }

    public void setDtTrong(int dtTrong) {
        this.dtTrong = dtTrong;
    }

    public float getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(float rentPrice) {
        this.rentPrice = rentPrice;
    }

    public float getPhiMG() {
        return phiMG;
    }

    public void setPhiMG(float phiMG) {
        this.phiMG = phiMG;
    }
}
