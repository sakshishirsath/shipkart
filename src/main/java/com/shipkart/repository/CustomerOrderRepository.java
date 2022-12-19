package com.shipkart.repository;

import com.shipkart.entity.CustomerOrder;
import com.shipkart.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    List<CustomerOrder> findCustomerOrdersByStatus(OrderStatus orderStatus);

    Long countCustomerOrderByUserId(Long userId);
}
