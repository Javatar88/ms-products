package com.adib.products.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Author By Mahmoud Ahmed Said
 * ErrorResponse class retrieved when an exception is thrown
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private LocalDateTime dateTime;


}