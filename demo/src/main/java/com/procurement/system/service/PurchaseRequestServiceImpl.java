package com.procurement.system.service;

import com.procurement.system.dto.PurchaseRequestRequest;
import com.procurement.system.dto.PurchaseRequestResponse;
import com.procurement.system.entity.Product;
import com.procurement.system.entity.PurchaseRequest;
import com.procurement.system.entity.User;
import com.procurement.system.enums.RequestStatus;
import com.procurement.system.repository.ProductRepository;
import com.procurement.system.repository.PurchaseRequestRepository;
import com.procurement.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseRequestServiceImpl implements PurchaseRequestService {

    @Autowired
    private PurchaseRequestRepository purchaseRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public PurchaseRequestResponse raiseRequest(PurchaseRequestRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        PurchaseRequest purchaseRequest = new PurchaseRequest();

        purchaseRequest.setUser(user);
        purchaseRequest.setProduct(product);
        purchaseRequest.setQuantity(request.getQuantity());
        purchaseRequest.setJustification(request.getJustification());

        double totalAmount = product.getPrice() * request.getQuantity();
        purchaseRequest.setTotalAmount(totalAmount);

        purchaseRequest.setStatus(RequestStatus.PENDING);
        purchaseRequest.setCreatedAt(LocalDateTime.now());
        purchaseRequest.setUpdatedAt(LocalDateTime.now());

        PurchaseRequest savedRequest = purchaseRequestRepository.save(purchaseRequest);

        emailService.sendRequestRaisedEmail(
                user.getEmail(),
                user.getName(),
                product.getProductName(),
                request.getQuantity(),
                totalAmount
        );

        return mapToResponse(savedRequest, "Purchase Request Raised Successfully");
    }

    @Override
    public List<PurchaseRequestResponse> getMyRequests(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return purchaseRequestRepository.findByUser(user)
                .stream()
                .map(request -> mapToResponse(request, "Success"))
                .toList();
    }

    @Override
    public List<PurchaseRequestResponse> getAllRequests() {

        return purchaseRequestRepository.findAll()
                .stream()
                .map(request -> mapToResponse(request, "Success"))
                .toList();
    }

    @Override
    public PurchaseRequestResponse approveRequest(Long requestId, String remarks) {

        PurchaseRequest request = purchaseRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Purchase Request not found"));

        request.setStatus(RequestStatus.APPROVED);
        request.setRemarks(remarks);
        request.setUpdatedAt(LocalDateTime.now());

        PurchaseRequest savedRequest = purchaseRequestRepository.save(request);

        emailService.sendApprovalEmail(
                request.getUser().getEmail(),
                request.getUser().getName(),
                request.getProduct().getProductName()
        );

        return mapToResponse(savedRequest, "Purchase Request Approved");
    }

    @Override
    public PurchaseRequestResponse rejectRequest(Long requestId, String remarks) {

        PurchaseRequest request = purchaseRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Purchase Request not found"));

        request.setStatus(RequestStatus.REJECTED);
        request.setRemarks(remarks);
        request.setUpdatedAt(LocalDateTime.now());

        PurchaseRequest savedRequest = purchaseRequestRepository.save(request);

        emailService.sendRejectionEmail(
                request.getUser().getEmail(),
                request.getUser().getName(),
                request.getProduct().getProductName(),
                remarks
        );

        return mapToResponse(savedRequest, "Purchase Request Rejected");
    }

    private PurchaseRequestResponse mapToResponse(PurchaseRequest request, String message) {

        PurchaseRequestResponse response = new PurchaseRequestResponse();

        response.setRequestId(request.getRequestId());
        response.setEmployeeName(request.getUser().getName());
        response.setProductName(request.getProduct().getProductName());
        response.setQuantity(request.getQuantity());
        response.setTotalAmount(request.getTotalAmount());
        response.setJustification(request.getJustification());
        response.setStatus(request.getStatus());
        response.setCreatedAt(request.getCreatedAt());
        response.setMessage(message);

        return response;
    }
}