package com.houdaoul.ecom.onlineshopping.service;

import com.houdaoul.ecom.onlineshopping.domain.Cart;
import com.houdaoul.ecom.onlineshopping.domain.CartItem;
import com.houdaoul.ecom.onlineshopping.record.CartRecord;
import com.houdaoul.ecom.onlineshopping.util.Constants;
import com.houdaoul.ecom.onlineshopping.util.JsonSerializer;
import com.houdaoul.ecom.onlineshopping.util.RandomIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    private final RedisTemplate<String, Object> redisTemplate;

    private final CartItemService cartItemService;

    private final ProductService productService;

    private final RandomIdGenerator randomIdGenerator;

    private final JsonSerializer serializer;

    public CartService(RedisTemplate<String, Object> redisTemplate, CartItemService cartItemService, ProductService productService, RandomIdGenerator randomIdGenerator, JsonSerializer serializer) {
        this.redisTemplate = redisTemplate;
        this.cartItemService = cartItemService;
        this.productService = productService;
        this.randomIdGenerator = randomIdGenerator;
        this.serializer = serializer;
    }

    public Cart createCart() {
        UUID id = randomIdGenerator.generateRandomId();
        String key = Constants.REDIS_CART + id;
        var cartRecord = new CartRecord(id, new ArrayList<>(), 0.0);

        redisTemplate.opsForValue().set(key, serializer.serialize(cartRecord));
        return Cart.builder()
                .id(id)
                .price(cartRecord.price())
                .cartItems(cartRecord.cartItems())
                .build();
    }

    public Cart getCart(UUID cartId) {
        String key = Constants.REDIS_CART + cartId;
        CartRecord cartRecord = serializer.deserialize((String) redisTemplate.opsForValue().get(key), CartRecord.class);

        return Cart.builder()
                .id(cartId)
                .price(cartRecord.price())
                .cartItems(cartRecord.cartItems())
                .build();
    }

    public Cart addProductToCart(UUID cartId, Long productId, int quantity) {
        Cart cart = getCart(cartId);
        List<CartItem> cartItems = cart.getCartItems();

        CartItem cartItem = cartItems.stream().filter(item -> item.getProductId().equals(productId))
                .findFirst().map(item -> {
                    item.setQuantity(item.getQuantity() + quantity);
                    return item;
                })
                .orElseGet(() -> {
                    var item = CartItem.builder()
                            .productId(productId)
                            .quantity(quantity)
                            .build();
                    cart.getCartItems().add(item);
                    return item;
                });

        cart.setPrice(cart.getPrice() + (quantity * productService.getProduct(productId).getPrice()));
        updateCart(cart);
        return getCart(cartId);
    }


    public void updateCart(Cart cart) {
        String key = Constants.REDIS_CART + cart.getId();
        CartRecord cartRecord = new CartRecord(cart.getId(), cart.getCartItems(), cart.getPrice());
        redisTemplate.opsForValue().set(key, serializer.serialize(cartRecord));
    }
}
