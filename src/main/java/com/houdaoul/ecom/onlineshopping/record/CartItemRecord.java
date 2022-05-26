package com.houdaoul.ecom.onlineshopping.record;

import java.util.UUID;

public record CartItemRecord(UUID cartItemId, long productId, int quantity) {
}
