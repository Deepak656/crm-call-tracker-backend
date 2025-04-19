package com.example.crm.controller;

import com.example.crm.dto.CallLogDTO;
import com.example.crm.model.CallLog;
import com.example.crm.repository.CallLogRepository;
import com.example.crm.repository.CustomerRepository;
import com.example.crm.repository.EmployeeRepository;
import com.example.crm.model.Customer;
import com.example.crm.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/calls")
public class CallLogController {
    @Autowired private CallLogRepository callLogRepo;
    @Autowired private CustomerRepository customerRepo;
    @Autowired private EmployeeRepository employeeRepo;

    @PostMapping
    public ResponseEntity<?> logCall(@RequestBody CallLogDTO dto) {
        Optional<Customer> customerOpt = customerRepo.findByMobile(dto.customerMobile());
        Optional<Employee> employeeOpt = employeeRepo.findById(dto.employeeId());

        if (customerOpt.isEmpty() || employeeOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid customer or employee");
        }

        CallLog call = new CallLog();
        call.setCustomer(customerOpt.get());
        call.setEmployee(employeeOpt.get());
        call.setTimestamp(dto.timestamp());
        call.setDuration(dto.duration());

        return ResponseEntity.ok(callLogRepo.save(call));
    }

    @GetMapping
    public List<CallLog> getCallLogs(@RequestParam String number) {
        return callLogRepo.findByCustomer_Mobile(number);
    }
}