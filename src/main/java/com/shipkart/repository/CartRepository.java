package com.shipkart.repository;

import com.shipkart.entity.Cart;
import com.shipkart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart getCartByUserId(Long user_id);
}
