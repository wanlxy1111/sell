package com.neuedu.sell.service;

import com.neuedu.sell.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    //查询所有类目
    List<ProductCategory> findAll();
    //根据id查询
    ProductCategory findOne(Integer categoryId);
    //添加/修改
    ProductCategory save(ProductCategory productCategory);
    //根据类目编号的集合来查询
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
