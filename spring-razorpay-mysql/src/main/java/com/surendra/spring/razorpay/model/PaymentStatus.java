package com.surendra.spring.razorpay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentStatus {

    @Id
    private String paymentId;

    private String orderId;
    private String signature;
    private String description;
    private String status;
    private String source;
    private String reason;
    private String step;

}
