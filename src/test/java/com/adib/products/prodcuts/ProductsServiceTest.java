package com.adib.products.prodcuts;

import com.adib.products.controller.ProductsController;
import com.adib.products.dto.ProductDto;
import com.adib.products.model.Product;
import com.adib.products.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductsController.class)
public class ProductsServiceTest {
    @MockBean
    ProductService productService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ObjectMapper mapper;

    ProductDto productDto;

    ProductDto updatedProductDto;

    Product product;

    @BeforeEach
    void setup() {
        productDto = new ProductDto(1L, "Think-pad x1",
                "Lenovo Think-Pad X1 - intel core I7 - 16GB memory - 512gb ssd", 350.0);

        updatedProductDto = new ProductDto(1L, "Lenovo IdeaPad S145",
                "Lenovo IdeaPad S145 - intel core I5 - 8GB memory - 512gb ssd", 300.0);

        product = modelMapper.map(productDto, Product.class);

    }

    @Test
    void testGetAllProducts() throws Exception {
        List<ProductDto> productDtoList = Arrays.asList(productDto);
        Mockito.when(productService.findAllProducts()).thenReturn(productDtoList);
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Think-pad x1")))
                .andExpect(jsonPath("$[0].description", Matchers.is("Lenovo Think-Pad X1 - intel core I7 - 16GB memory - 512gb ssd")))
                .andExpect(jsonPath("$[0].price", Matchers.is(350.0)));
    }

    @Test
    void testFindProductsById() throws Exception {
        Mockito.when(productService.findProductById(1L)).thenReturn(productDto);
        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is("Think-pad x1")))
                .andExpect(jsonPath("$.description", Matchers.is("Lenovo Think-Pad X1 - intel core I7 - 16GB memory - 512gb ssd")))
                .andExpect(jsonPath("$.price", Matchers.is(350.0)));

    }

    @Test
    void testSaveProducts() throws Exception {
        Mockito.when(productService.saveProduct(productDto)).thenReturn(productDto);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(product));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(content().string("New Product is created with Id: " + 1L));

    }

    @Test
    void testDeleteProductsById() throws Exception {
        Mockito.when(productService.findProductById(product.getId())).thenReturn(productDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/products/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string("Product with Id : " + 1L + " has been deleted"));

    }


    @Test
    void testUpdateProductsById() throws Exception {
        Product updatedProduct = this.modelMapper.map(updatedProductDto, Product.class);
        Mockito.when(productService.updateProductById(1L, productDto)).thenReturn(updatedProductDto);


        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/products/1")
                        .content((this.mapper.writeValueAsString(productDto)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Lenovo IdeaPad S145"))
                .andExpect(jsonPath("$.description", Matchers.is("Lenovo IdeaPad S145 - intel core I5 - 8GB memory - 512gb ssd")));

    }


}
