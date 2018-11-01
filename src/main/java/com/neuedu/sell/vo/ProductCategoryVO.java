package com.neuedu.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.neuedu.sell.entity.ProductInfo;
import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryVO   {

    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("value")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
