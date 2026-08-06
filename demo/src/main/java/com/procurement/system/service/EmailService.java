package com.procurement.system.service;

public interface EmailService {

    void sendRequestRaisedEmail(String to,
                                String employeeName,
                                String productName,
                                Integer quantity,
                                Double total);

    void sendApprovalEmail(String to,
                           String employeeName,
                           String productName);

    void sendRejectionEmail(String to,
                            String employeeName,
                            String productName,
                            String remarks);

    void sendApprovalRequestToManager(
            String employeeName,
            String employeeEmail,
            String productName,
            Integer quantity,
            Double totalAmount
    );
}