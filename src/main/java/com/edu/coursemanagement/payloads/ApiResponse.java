package com.edu.coursemanagement.payloads;

public record ApiResponse<T>(    
    int status,
    String message,
    T data
    ) {
} 
