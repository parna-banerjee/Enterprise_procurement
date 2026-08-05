package com.procurement.system.service;

import com.procurement.system.dto.PurchaseRequestRequest;
import com.procurement.system.dto.PurchaseRequestResponse;

import java.util.List;

public interface PurchaseRequestService {

    PurchaseRequestResponse raiseRequest(PurchaseRequestRequest request);

    List<PurchaseRequestResponse> getMyRequests(Long userId);

    List<PurchaseRequestResponse> getAllRequests();

    PurchaseRequestResponse approveRequest(Long requestId, String remarks);

    PurchaseRequestResponse rejectRequest(Long requestId, String remarks);

}