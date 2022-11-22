package com.adib.products.service;


import com.adib.products.dto.ProductDto;

import java.util.List;

/**
 * Author Mahmoud Ahmed Said
 * this class represent the business layer interface  which is used as an abstraction layer to ProductServiceImpl
 */
public interface ProductService {

    /**
     * method findAllProducts
     *
     * @return List of ProductDto
     */
    List<ProductDto> findAllProducts();

    /**
     * method findProductById
     *
     * @param id
     * @return ProductDto
     */
    ProductDto findProductById(Long id);

    /**
     * method saveProduct
     *
     * @param productDto
     * @return ProductDto
     */
    ProductDto saveProduct(ProductDto productDto);

    /**
     * @param id
     * @param productDto
     * @return updated ProductDto
     */
    ProductDto updateProductById(Long id, ProductDto productDto);

    /**
     * method deleteProductById
     *
     * @param id
     * @return void
     */
    void deleteProductById(Long id);

}
