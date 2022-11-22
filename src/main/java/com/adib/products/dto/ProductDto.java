package com.adib.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Author Mahmoud Ahmed Said
 * this Class represent Data transfer object that will be retrieved from the end point
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    @NotNull(message = "Please provide Product Name")
    @Size(min = 2)
    private String name;

    @NotNull(message = "Please provide Product description")
    @Size(min = 2, max = 500)
    private String description;

    @NotNull(message = "Please provide Product price")
    private Double price;


}
