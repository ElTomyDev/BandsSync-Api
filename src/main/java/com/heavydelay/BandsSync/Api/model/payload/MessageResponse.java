package com.heavydelay.BandsSync.Api.model.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {
    private String message;
    private Integer status;
    private Object objectResponse;
}
