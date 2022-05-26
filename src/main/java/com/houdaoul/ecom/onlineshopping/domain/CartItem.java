package com.houdaoul.ecom.onlineshopping.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@RedisHash(value = "cartItem", timeToLive = 36000)
public class CartItem {

    private Long productId;

    private int quantity;
}
