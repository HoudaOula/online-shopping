package com.houdaoul.ecom.onlineshopping.service;

import com.houdaoul.ecom.onlineshopping.domain.Product;
import com.houdaoul.ecom.onlineshopping.dto.ProductDto;
import com.houdaoul.ecom.onlineshopping.exception.ProductDoesNotExistException;
import com.houdaoul.ecom.onlineshopping.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(ProductDoesNotExistException::new);
    }

    public Long createProduct(ProductDto productDto) {
        var product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .build();

        product = productRepository.save(product);
        return product.getId();
    }
}
