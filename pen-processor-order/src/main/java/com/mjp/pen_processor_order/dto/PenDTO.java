package com.mjp.pen_processor_order.dto;

public record PenDTO(Ink ink,
                     Ball ball,
                     BallSupport ballSupport,
                     BallSupportCoupler ballSupportCoupler,
                     BallSupportCoupleInkTube ballSupportCouplesInkTube,
                     ExternalTube externalTube,
                     InternalTube internalTube,
                     OuterTubeCover outerTubeCover,
                     TipCap tipCap,
                     Integer quantity) {


}
