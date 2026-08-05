package com.procurement.system.controller;

import com.procurement.system.dto.PurchaseRequestRequest;
import com.procurement.system.dto.PurchaseRequestResponse;
import com.procurement.system.service.PurchaseRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
@CrossOrigin("*")
public class PurchaseRequestController {

    @Autowired
    private PurchaseRequestService purchaseRequestService;

    @PostMapping
    public PurchaseRequestResponse raiseRequest(
            @RequestBody PurchaseRequestRequest request) {

        return purchaseRequestService.raiseRequest(request);
    }

    @GetMapping("/user/{userId}")
    public List<PurchaseRequestResponse> getMyRequests(
            @PathVariable Long userId) {

        return purchaseRequestService.getMyRequests(userId);
    }

    @GetMapping
    public List<PurchaseRequestResponse> getAllRequests() {

        return purchaseRequestService.getAllRequests();
    }

    @PutMapping("/{requestId}/approve")
    public PurchaseRequestResponse approveRequest(
            @PathVariable Long requestId,
            @RequestParam String remarks) {

        return purchaseRequestService.approveRequest(requestId, remarks);
    }

    @PutMapping("/{requestId}/reject")
    public PurchaseRequestResponse rejectRequest(
            @PathVariable Long requestId,
            @RequestParam String remarks) {

        return purchaseRequestService.rejectRequest(requestId, remarks);
    }
}