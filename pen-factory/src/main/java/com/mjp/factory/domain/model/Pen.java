package com.mjp.factory.domain.model;

import java.util.UUID;


public abstract class Pen {

    private UUID uuid;
    private Ball ball;
    private BallSupport ballSupport;
    private BallSupportCouplerInkTube ballSupportCouplerInkTube;
    private ExternalTube externalTube;
    private InternalTube internalTube;
    private Ink ink;
    private OuterTubeCover outerTubeCover;
    private TipCap tipCap;
    private Integer orderNumber;

    public Pen() {
    }

    public Pen(Ball ball, BallSupport ballSupport, BallSupportCouplerInkTube ballSupportCouplerInkTube,
               ExternalTube externalTube, Ink ink, InternalTube internalTube, Integer orderNumber,
               OuterTubeCover outerTubeCover, TipCap tipCap, UUID uuid) {
        this.ball = ball;
        this.ballSupport = ballSupport;
        this.ballSupportCouplerInkTube = ballSupportCouplerInkTube;
        this.externalTube = externalTube;
        this.ink = ink;
        this.internalTube = internalTube;
        this.orderNumber = orderNumber;
        this.outerTubeCover = outerTubeCover;
        this.tipCap = tipCap;
        this.uuid = uuid;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public BallSupport getBallSupport() {
        return ballSupport;
    }

    public void setBallSupport(BallSupport ballSupport) {
        this.ballSupport = ballSupport;
    }

    public BallSupportCouplerInkTube getBallSupportCouplerInkTube() {
        return ballSupportCouplerInkTube;
    }

    public void setBallSupportCouplerInkTube(BallSupportCouplerInkTube ballSupportCouplerInkTube) {
        this.ballSupportCouplerInkTube = ballSupportCouplerInkTube;
    }

    public ExternalTube getExternalTube() {
        return externalTube;
    }

    public void setExternalTube(ExternalTube externalTube) {
        this.externalTube = externalTube;
    }

    public Ink getInk() {
        return ink;
    }

    public void setInk(Ink ink) {
        this.ink = ink;
    }

    public InternalTube getInternalTube() {
        return internalTube;
    }

    public void setInternalTube(InternalTube internalTube) {
        this.internalTube = internalTube;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OuterTubeCover getOuterTubeCover() {
        return outerTubeCover;
    }

    public void setOuterTubeCover(OuterTubeCover outerTubeCover) {
        this.outerTubeCover = outerTubeCover;
    }

    public TipCap getTipCap() {
        return tipCap;
    }

    public void setTipCap(TipCap tipCap) {
        this.tipCap = tipCap;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
