package com.procurement.system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "approval_hierarchy")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprovalHierarchy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approval_id")
    private Long approvalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User approver;

    private Integer approvalLevel;
}