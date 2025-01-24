package com.mjp.pen_processor_order.dto;

import java.util.UUID;

public record PenDTO(Ink ink,
                     Ball ball,
                     BallSupport ballSupport,
                     BallSupportCoupler ballSupportCoupler,
                     BallSupportCoupleInkTube ballSupportCouplesInkTube,
                     ExternalTube externalTube,
                     InternalTube internalTube,
                     OuterTubeCover outerTubeCover,
                     TipCap tipCap
                    ) {
}
