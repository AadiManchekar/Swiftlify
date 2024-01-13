package com.aadi.ProductService.service;

import static org.springframework.beans.BeanUtils.copyProperties;

import com.aadi.ProductService.entity.Product;
import com.aadi.ProductService.exception.ProductServiceCustomException;
import com.aadi.ProductService.model.ProductRequest;
import com.aadi.ProductService.model.ProductResponse;
import com.aadi.ProductService.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public long addProduct(ProductRequest productRequest) {

    Product product =
        Product.builder()
            .productName(productRequest.getName())
            .quantity(productRequest.getQuantity())
            .price(productRequest.getPrice())
            .build();

    log.info("Adding Product: " + product.toString());
    productRepository.save(product);
    log.info("product created");

    return product.getProductId();
  }

  @Override
  public ProductResponse getProductById(long productId) {
    Product product =
        productRepository
            .findById(productId)
            .orElseThrow(
                () ->
                    new ProductServiceCustomException(
                        "Product with given id not found", "PRODUCT_NOT_FOUND"));

    ProductResponse productResponse = new ProductResponse();
    copyProperties(product, productResponse);
    return productResponse;
  }
}
