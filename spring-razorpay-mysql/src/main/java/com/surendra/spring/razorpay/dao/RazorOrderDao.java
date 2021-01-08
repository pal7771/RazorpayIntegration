package com.surendra.spring.razorpay.dao;

import com.surendra.spring.razorpay.model.PaymentStatus;
import com.surendra.spring.razorpay.model.RazorOrder;
import org.springframework.data.repository.CrudRepository;

public interface RazorOrderDao extends CrudRepository<RazorOrder, String> {

}
