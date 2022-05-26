package com.houdaoul.ecom.onlineshopping.record;

import com.houdaoul.ecom.onlineshopping.domain.CartItem;

import java.util.List;
import java.util.UUID;

public record CartRecord(UUID id, List<CartItem> cartItems, double price) {
}
