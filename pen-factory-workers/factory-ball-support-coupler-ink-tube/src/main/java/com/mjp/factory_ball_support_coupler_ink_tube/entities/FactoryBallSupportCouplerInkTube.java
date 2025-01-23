package com.mjp.factory_ball_support_coupler_ink_tube.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_factory_ball_support_coupler_ink_tube")
public class FactoryBallSupportCouplerInkTube {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private String materialType;

    public FactoryBallSupportCouplerInkTube() {
    }

    public FactoryBallSupportCouplerInkTube(UUID id, String description, String materialType) {
        this.id = id;
        this.description = description;
        this.materialType = materialType;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }
}
