package com.adib.products.service.impl;


import com.adib.products.controller.ProductsController;
import com.adib.products.dto.ProductDto;
import com.adib.products.exception.ProductNotFoundException;
import com.adib.products.model.Product;
import com.adib.products.repository.ProductRepository;
import com.adib.products.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author Mahmoud Ahmed Said
 * the ProductServiceImpl is the implementation ProductService which have all the business logic
 */
@Service
public class ProductServiceImpl implements ProductService {

    Logger logger = LoggerFactory.getLogger(ProductsController.class);
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * method findAllProducts
     * @return list of ProductDto
     */
    @Override
    public List<ProductDto> findAllProducts() {
        logger.info("inside findAllProducts in Service impl ");
        List<Product> products = productRepository.findAll();
        logger.info("after findAllProducts retrieved all Products");
        List<ProductDto> productDto = products.stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return productDto;
    }

    /**
     * method findProductById  method take an productId and return product object
     * @param id
     * @return ProductDto
     */
    @Override
    public ProductDto findProductById(Long id) {
        logger.info("inside findProductById in Service impl");
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());
        logger.info("return Product object");
        return modelMapper.map(product, ProductDto.class);
    }

    /**
     * method saveProduct saves product to theDB
     *
     * @param productDto
     * @return ProductDto
     */
    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        logger.info("inside saveProduct in Service impl");
        //convert DTO to entity  and save Product to DB
        Product product = modelMapper.map(productDto, Product.class);
        logger.info("return ProductDto object after saving to DB");
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);

    }

    /**
     * method updateProductById takes ProductId and Product object to update object data in DB
     *
     * @param id
     * @param productDto
     * @return updated ProductDto
     */
    @Override
    public ProductDto updateProductById(Long id, ProductDto productDto) {
        logger.info("inside updateProductById in Service impl");
        if (id == null) {
            throw new ProductNotFoundException();
        } else {
            // convert DTO to Entity
            Product productRequest = modelMapper.map(productDto, Product.class);

            Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());
            logger.info("set new Product values after retrieving to be updated");
            if(StringUtils.isNotEmpty(productRequest.getName())){
                product.setName(productRequest.getName());
            }
            if(StringUtils.isNotEmpty(productRequest.getDescription())){
                product.setDescription(productRequest.getDescription());
            }
            if((productRequest.getPrice() >0.0)){
                product.setPrice(productRequest.getPrice());
            }
            logger.info("save Product to DB");
            Product updateProduct = productRepository.save(product);

            return modelMapper.map(updateProduct, ProductDto.class);
        }

    }

    /**
     * method deleteProductById take an input id and delete the Product with that Id
     *
     * @param id
     * @return void
     */
    @Override
    public void deleteProductById(Long id) {
        logger.info("inside deleteProductById in Service impl");
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            logger.info("Product is deleted successfully");
        } else {
            throw new ProductNotFoundException();
        }

    }

}