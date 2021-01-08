package com.surendra.spring.razorpay.dao;

import com.surendra.spring.razorpay.model.PaymentStatus;
import org.springframework.data.repository.CrudRepository;

public interface PaymentStatusDao extends CrudRepository<PaymentStatus, String> {

}
