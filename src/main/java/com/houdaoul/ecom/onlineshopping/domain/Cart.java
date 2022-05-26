package com.houdaoul.ecom.onlineshopping.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@RedisHash(value = "cart", timeToLive = 36000)
public class Cart {

    private UUID id;

    private List<CartItem> cartItems = new ArrayList<>();

    private double price;
}
