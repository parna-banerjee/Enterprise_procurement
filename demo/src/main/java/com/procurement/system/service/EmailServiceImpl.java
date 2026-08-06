package com.procurement.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${manager.email}")
    private String managerEmail;

    @Override
    public void sendRequestRaisedEmail(String to,
                                       String employeeName,
                                       String productName,
                                       Integer quantity,
                                       Double total) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(to);
        mail.setSubject("Purchase Request Submitted");

        mail.setText(
                "Hello " + employeeName +
                        "\n\nYour purchase request has been submitted successfully." +
                        "\n\nProduct : " + productName +
                        "\nQuantity : " + quantity +
                        "\nTotal Amount : ₹" + total +
                        "\nStatus : PENDING"
        );

        mailSender.send(mail);
    }

    @Override
    public void sendApprovalEmail(String to,
                                  String employeeName,
                                  String productName) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(to);
        mail.setSubject("Purchase Request Approved");

        mail.setText(
                "Hello " + employeeName +
                        "\n\nYour request for " + productName +
                        " has been APPROVED."
        );

        mailSender.send(mail);
    }

    @Override
    public void sendRejectionEmail(String to,
                                   String employeeName,
                                   String productName,
                                   String remarks) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(to);
        mail.setSubject("Purchase Request Rejected");

        mail.setText(
                "Hello " + employeeName +
                        "\n\nYour request for " + productName +
                        " has been REJECTED." +
                        "\nReason : " + remarks
        );

        mailSender.send(mail);
    }

    @Override
    public void sendApprovalRequestToManager(
            String employeeName,
            String employeeEmail,
            String productName,
            Integer quantity,
            Double totalAmount) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(managerEmail);
        message.setSubject("Purchase Request Approval Required");

        message.setText(
                "Hello Manager,\n\n" +
                        "A new purchase request has been raised.\n\n" +

                        "Employee Name : " + employeeName + "\n" +
                        "Employee Email: " + employeeEmail + "\n" +
                        "Product       : " + productName + "\n" +
                        "Quantity      : " + quantity + "\n" +
                        "Total Amount  : ₹" + totalAmount + "\n\n" +

                        "Kindly review and approve/reject this request from the Procurement Portal.\n\n" +

                        "Regards,\n" +
                        "Enterprise Procurement System"
        );

        mailSender.send(message);
    }
}