package com.procurement.system.dto;

import com.procurement.system.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequestResponse {

    private Long requestId;

    private String employeeName;

    private String productName;

    private Integer quantity;

    private Double totalAmount;

    private String justification;

    private RequestStatus status;

    private LocalDateTime createdAt;

    private String message;

}