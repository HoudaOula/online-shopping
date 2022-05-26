package com.houdaoul.ecom.onlineshopping.controller;

import com.houdaoul.ecom.onlineshopping.domain.Cart;
import com.houdaoul.ecom.onlineshopping.dto.ProductAddToCartDto;
import com.houdaoul.ecom.onlineshopping.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@CrossOrigin
@RequestMapping("/api/carts")
@RestController
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Cart> createCart() {
        return ResponseEntity.ok().body(cartService.createCart());
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable UUID cartId) {
        return ResponseEntity.ok(cartService.getCart(cartId));
    }

    @PostMapping("/product")
    public ResponseEntity<Cart> addProductToCart(@RequestBody ProductAddToCartDto productAddToCartDto) {
        return ResponseEntity.ok(cartService.addProductToCart(productAddToCartDto.getCartId(), productAddToCartDto.getProductId(), productAddToCartDto.getQuantity()));
    }
}
