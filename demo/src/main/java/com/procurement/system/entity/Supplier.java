package com.procurement.system.entity;

import com.procurement.system.enums.SupplierStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "supplier")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Long supplierId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private String name;
    private String phone;
    private String address;
    private String email;

    @Column(name = "gst_number")
    private String gstNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SupplierStatus status;

    private Double rating;
    private String feedback;

    @Column(name = "created_date", updatable = false)
    private java.time.LocalDateTime createdDate;

    @Column(name = "updated_date")
    private java.time.LocalDateTime updatedDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = java.time.LocalDateTime.now();
        this.updatedDate = java.time.LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = java.time.LocalDateTime.now();
    }
}