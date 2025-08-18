package com.javabykhang.project2pf.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rentarea")
public class RentareaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity building;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
