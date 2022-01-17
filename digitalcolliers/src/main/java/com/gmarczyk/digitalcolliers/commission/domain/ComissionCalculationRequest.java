package com.gmarczyk.digitalcolliers.commission.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComissionCalculationRequest {

    Long customerId;
    BigDecimal calculatedTotalCommission;
    Instant commissionCalculationDate;

}
