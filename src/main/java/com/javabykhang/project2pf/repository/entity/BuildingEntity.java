package com.javabykhang.project2pf.repository.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "street")
    private String street;

    @Column(name = "ward")
    private String ward;

    @Column(name = "numberOfBasement")
    private Long numberOfBasement;

    @Column(name = "floorArea")
    private Long floorArea;

    @Column(name = "rentPrice")
    private Long rentPrice;

    @Column(name = "managerName")
    private String managerName;

    @Column(name = "managerPhoneNumber")
    private String managerPhoneNumber;

    @ManyToOne
    @JoinColumn(name = "districtId")
    private DistrictEntity district;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    private List<RentareaEntity> rentareaEntities = new ArrayList<>();

    @Column(name = "rentpricedescription")
    private String rentpricedescription;

    public Long getId() {
        return id;
    }

    public BuildingEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BuildingEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public BuildingEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getWard() {
        return ward;
    }

    public BuildingEntity setWard(String ward) {
        this.ward = ward;
        return this;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public BuildingEntity setNumberOfBasement(Long numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
        return this;
    }

    public Long getFloorArea() {
        return floorArea;
    }

    public BuildingEntity setFloorArea(Long floorArea) {
        this.floorArea = floorArea;
        return this;
    }

    public Long getRentPrice() {
        return rentPrice;
    }

    public BuildingEntity setRentPrice(Long rentPrice) {
        this.rentPrice = rentPrice;
        return this;
    }

    public String getManagerName() {
        return managerName;
    }

    public BuildingEntity setManagerName(String managerName) {
        this.managerName = managerName;
        return this;
    }

    public String getManagerPhoneNumber() {
        return managerPhoneNumber;
    }

    public BuildingEntity setManagerPhoneNumber(String managerPhoneNumber) {
        this.managerPhoneNumber = managerPhoneNumber;
        return this;
    }

    public DistrictEntity getDistrict() {
        return district;
    }

    public BuildingEntity setDistrict(DistrictEntity district) {
        this.district = district;
        return this;
    }

    public String getRentpricedescription() {
        return rentpricedescription;
    }

    public BuildingEntity setRentpricedescription(String rentpricedescription) {
        this.rentpricedescription = rentpricedescription;
        return this;
    }

    public List<RentareaEntity> getRentareaEntities() {
        return rentareaEntities;
    }

    public BuildingEntity setRentareaEntities(List<RentareaEntity> rentareaEntities) {
        this.rentareaEntities = rentareaEntities;
        return this;
    }
}