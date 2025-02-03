package com.mjp.factory_ball.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_factory_pen")
public class Pen {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private Ball factoryBall;
    private BallSupport factoryBallSupport;
    private BallSupportCouplerInkTube ballSupportCouplerInkTube;
    private ExternalTube externalTube;
    private InternalTube internalTube;
    private Ink ink;
    private OuterTubeCover outerTubeCover;
    private TipCap tipCap;


}
