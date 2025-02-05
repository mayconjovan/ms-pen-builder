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
@Table(name = "tb_pen")
public class JpaPenEntity extends Pen {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID uuid;

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

}
