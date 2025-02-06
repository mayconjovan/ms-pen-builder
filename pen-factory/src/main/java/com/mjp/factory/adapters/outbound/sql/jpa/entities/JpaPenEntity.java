package com.mjp.factory.adapters.outbound.sql.jpa.entities;

import com.mjp.factory.domain.model.Pen;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pen")
public class JpaPenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID uuid;

    private Integer orderNumber;
    private Integer itemNumber;
    private Integer quantity;

    @OneToOne(mappedBy = "pen", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private JpaBallEntity ball;

    @OneToOne(mappedBy = "pen", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private JpaBallSupportEntity ballSupport;

    @OneToOne(mappedBy = "pen", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private JpaBallSupportCouplerInkTubeEntity ballSupportCouplerInkTube;

    @OneToOne(mappedBy = "pen", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private JpaExternalTubeEntity externalTube;

    @OneToOne(mappedBy = "pen", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private JpaInternalTubeEntity internalTube;

    @OneToOne(mappedBy = "pen", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private JpaInkEntity ink;

    @OneToOne(mappedBy = "pen", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private JpaOuterTubeCoverEntity outerTubeCover;

    @OneToOne(mappedBy = "pen", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private JpaTipCapEntity tipCap;


    public JpaPenEntity(Pen pen) {
        this.ball = new JpaBallEntity(pen.getBall());
        this.ballSupport = new JpaBallSupportEntity(pen.getBallSupport());
        this.ballSupportCouplerInkTube = new JpaBallSupportCouplerInkTubeEntity(pen.getBallSupportCouplerInkTube());
        this.externalTube = new JpaExternalTubeEntity(pen.getExternalTube());
        this.ink = new JpaInkEntity(pen.getInk());
        this.internalTube = new JpaInternalTubeEntity(pen.getInternalTube());
        this.outerTubeCover = new JpaOuterTubeCoverEntity(pen.getOuterTubeCover());
        this.tipCap = new JpaTipCapEntity(pen.getTipCap());
        this.orderNumber = pen.getOrderNumber();
    }

}
