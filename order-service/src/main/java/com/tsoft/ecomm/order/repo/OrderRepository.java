package com.tsoft.ecomm.order.repo;

import com.tsoft.ecomm.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
