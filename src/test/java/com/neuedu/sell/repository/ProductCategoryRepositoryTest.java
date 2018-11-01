package com.neuedu.sell.repository;

import com.neuedu.sell.entity.ProductCategory;
import com.neuedu.sell.entity.ProductInfo;
import com.neuedu.sell.enums.ProductStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findAllTest(){
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        for (ProductCategory productCategory : productCategories) {
            System.out.println(productCategory);
        }
    }

    @Test
    public void saveTest(){
        ProductCategory p = new ProductCategory();
        p.setCategoryName("女生最爱");
        p.setCategoryType(2);
        productCategoryRepository.save(p);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(1,2,3);
        List<ProductCategory> productCategoryList = productCategoryRepository.findByCategoryTypeIn(list);
        for (ProductCategory productCategory : productCategoryList) {
            System.out.println(productCategory);
        }
    }
}