package com.houdaoul.ecom.onlineshopping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ProductAddToCartDto {

    private UUID cartId;

    private long productId;

    private int quantity;
}
