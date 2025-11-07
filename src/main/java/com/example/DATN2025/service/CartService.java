package com.example.DATN2025.service;

import com.example.DATN2025.dto.Cart.CartDto;
import com.example.DATN2025.dto.Order.OrderDto;
import com.example.DATN2025.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
//    Page<Cart> carts(Pageable pageable);
    List<CartDto> getAllCart();
    List<CartDto> getAllCartByAccountId();
    void addToCart(CartDto cartDto) throws NotFoundException;

    void updateCart(CartDto cartDto) throws NotFoundException;

    void orderUser(OrderDto orderDto);
    OrderDto orderAdmin(OrderDto orderDto);

    void deleteCart(Long id);
}
