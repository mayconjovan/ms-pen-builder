package com.mjp.pen_processor_order.dto;

public record PenDTO(Ink ink,
                     Ball ball,
                     BallSupport ballSupport,
                     BallSupportCoupler ballSupportCoupler,
                     BallSupportCouplesInkTube ballSupportCouplesInkTube,
                     ExternalTube externalTube,
                     InternalTube internalTube,
                     OuterTubeCover outerTubeCover,
                     TipCap tipCap) {


}
