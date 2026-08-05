package com.procurement.system.repository;

import com.procurement.system.entity.PurchaseRequest;
import com.procurement.system.entity.User;
import com.procurement.system.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, Long> {

    List<PurchaseRequest> findByUser(User user);

    List<PurchaseRequest> findByStatus(RequestStatus status);

    List<PurchaseRequest> findByUserAndStatus(User user, RequestStatus status);

}