package com.mjp.factory.adapters.outbound.sql.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjp.factory.domain.model.InternalTube;
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
@Table(name = "tb_internal_tube")
public class JpaInternalTubeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String description;
    private String material;
    private String color;
    private Integer orderNumber;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "ball_support", referencedColumnName = "id")
    private JpaPenEntity pen;

    public JpaInternalTubeEntity(InternalTube internalTube) {
        this.description = internalTube.getDescription();
        this.material = internalTube.getMaterial();
        this.orderNumber = internalTube.getOrderNumber();
        this.color = internalTube.getColor();
    }
}
