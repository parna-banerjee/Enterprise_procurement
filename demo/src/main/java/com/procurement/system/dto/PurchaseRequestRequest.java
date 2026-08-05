package com.procurement.system.dto;

import lombok.Data;

@Data
public class PurchaseRequestRequest {

    private Long userId;

    private Long productId;

    private Integer quantity;

    private String justification;

}