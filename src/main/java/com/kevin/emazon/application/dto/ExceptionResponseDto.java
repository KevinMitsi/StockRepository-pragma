package com.kevin.emazon.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponseDto {
    private String errorName;
    private String errorDescription;
    private HttpStatus statusCode;
}
