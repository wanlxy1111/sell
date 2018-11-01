package com.neuedu.sell.service;

import com.neuedu.sell.dto.CartDTO;
import com.neuedu.sell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    //查询所有在架商品
    List<ProductInfo> findUpAll();
    //查询所有商品包含分页
    Page<ProductInfo> findAll(Pageable pageable);
    //根据id查询商品
    ProductInfo findOne(String productInfoId);
    //添加/修改商品
    ProductInfo save(ProductInfo productInfo);
    //减少库存
    void decreaseStock(List<CartDTO> cartDTOList);
}
