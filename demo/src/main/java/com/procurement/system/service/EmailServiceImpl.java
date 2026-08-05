package com.procurement.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

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
}