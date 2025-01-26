package com.mjp.pen_processor_order.dto;


import com.mjp.pen_processor_order.dto.pen.*;

public record PensDetailsDTO(Integer itemNumber,
                             Ink ink,
                             Ball ball,
                             BallSupport ballSupport,
                             BallSupportCouplerInkTube ballSupportCouplerInkTube,
                             ExternalTube externalTube,
                             InternalTube internalTube,
                             OuterTubeCover outerTubeCover,
                             TipCap tipCap,
                             Integer quantity
                    ) {
}
