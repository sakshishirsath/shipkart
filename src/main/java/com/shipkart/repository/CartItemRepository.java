package com.shipkart.repository;

import com.shipkart.entity.Cart;
import com.shipkart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem getByCartAndProductId(Cart cart, Long productId);
}
