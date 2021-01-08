package com.surendra.spring.razorpay.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor

public class RazorOrder {

    @Id
    private String orderId;

    public RazorOrder(String orderId, String currency, int amount) {
        this.orderId = orderId;
        this.currency = currency;
        this.amount = amount;
    }

    private String currency;
    private int amount;
}
