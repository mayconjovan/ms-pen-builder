package com.mjp.factory.adapters.outbound.sql.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjp.factory.domain.model.BallSupportCouplerInkTube;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_ball_support_coupler_ink")
public class JpaBallSupportCouplerInkTubeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String description;
    private String color;
    private String material;
    private Integer orderNumber;


    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "ball_support", referencedColumnName = "id")
    private JpaPenEntity pen;


    public JpaBallSupportCouplerInkTubeEntity(BallSupportCouplerInkTube ballSupportCouplerInkTube) {
        this.description = ballSupportCouplerInkTube.getDescription();
        this.material = ballSupportCouplerInkTube.getMaterial();
        this.orderNumber = ballSupportCouplerInkTube.getOrderNumber();
        this.color = ballSupportCouplerInkTube.getColor();
    }
}
