package com.example.crm.repository;

import com.example.crm.model.CallLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CallLogRepository extends JpaRepository<CallLog, Long> {
    List<CallLog> findByCustomer_Mobile(String mobile);
}