package com.adib.products.controller;


import com.adib.products.dto.ProductDto;
import com.adib.products.service.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Author Mahmoud Ahmed Said
 * This is controller Class that can manage all Product operation
 */
@RestController
@RequestMapping("/api/v1")
public class ProductsController {

    Logger logger = LoggerFactory.getLogger(ProductsController.class);
    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * findAllProducts
     *
     * @return List of ProductDto
     */
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> findAllProducts() {
        logger.info("findAllProducts method call in ProductsController");
        List<ProductDto> productDto = productService.findAllProducts();
        logger.info("return all fetched Products with status ok in response entity");
        return ResponseEntity.ok().body(productDto);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable("id") Long id) {
        logger.info("findProductById method call to retrieve a Product by it is ID");
        ProductDto productDto = productService.findProductById(id);
        logger.info("return exact one Product by By Id passed");
        return ResponseEntity.ok().body(productDto);
    }

    @PostMapping("/products")
    public ResponseEntity<String> saveProduct(@Valid @RequestBody ProductDto productDto) {
        logger.info("saveProduct method call to save product");
        ProductDto savedProduct = productService.saveProduct(productDto);
        logger.info("return saved Product Id and  Http Status for created product");
        return new ResponseEntity<>("New Product is created with Id: " + savedProduct.getId(), HttpStatus.CREATED);
    }

    /**
     * @param id
     * @return
     */

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") Long id) {
        logger.info("deleteProductById method call to Delete product");
        productService.deleteProductById(id);
        logger.info("return response that the Product has been deleted from  DB");
        return new ResponseEntity<>("Product with Id : " + id + " has been deleted", HttpStatus.OK);

    }


    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto> updateProductById(@RequestBody ProductDto productDto, @PathVariable("id") Long id) {
        logger.info("updateProductById method call to update product data into DB");
        ProductDto updatedProduct = productService.updateProductById(id, productDto);
        logger.info("return responseEntity with updated Product");
        return ResponseEntity.ok().body(updatedProduct);
    }


}
