package com.example.crm.dto;

import java.time.LocalDateTime;

public record CallLogDTO(
    Long employeeId,
    String customerMobile,
    LocalDateTime timestamp,
    Long duration
) {}