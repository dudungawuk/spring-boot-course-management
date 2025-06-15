package com.edu.coursemanagement.dto.request;

import java.util.UUID;



public record EnrollmentRequest(
     UUID studentId,
     UUID courseOfferingId
) {
}
