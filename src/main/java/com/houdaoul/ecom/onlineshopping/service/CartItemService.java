package com.houdaoul.ecom.onlineshopping.service;

import org.springframework.stereotype.Service;

@Service
public class CartItemService {
/*
    private static final Logger logger = LoggerFactory.getLogger(CartItemService.class);

    private final RedisTemplate<String, Object> redisTemplate;

    private final RandomIdGenerator randomIdGenerator;

    private final JsonSerializer serializer;


    public CartItemService(RedisTemplate<String, Object> redisTemplate, RandomIdGenerator randomIdGenerator, JsonSerializer serializer) {
        this.redisTemplate = redisTemplate;
        this.randomIdGenerator = randomIdGenerator;
        this.serializer = serializer;
    }

    public CartItem createCartItem(long productId, int quantity) {
        return CartItem.builder()
                .productId(productId)
                .quantity(quantity)
                .build();
    }

    public CartItem getCartItem(UUID cartItemId) {
        String key = String.format("cartItem:%s", cartItemId);

        CartItemRecord item = serializer.deserialize((String) redisTemplate.opsForValue().get(key), CartItemRecord.class);
        return CartItem.builder()
                .productId(item.productId())
                .quantity(item.quantity())
                .build();
    }

    /**
     * This is the case when adding a new product to the cart
     * If the cart already contains the product, we update the cartItem by calling this function
     * Otherwise we create a new CartItem by calling the createCartItem() function
     *
     * @param cartItemId
     * @param productId

    public void updateCartItem(UUID cartItemId, long productId) {
        CartItem cartItem = getCartItem(cartItemId);
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        String key = String.format("cartItem:%s", cartItemId);

        var item = new CartItemRecord(cartItemId, productId, cartItem.getQuantity());
        redisTemplate.opsForValue().set(key, serializer.serialize(item));
    }


 */
}
