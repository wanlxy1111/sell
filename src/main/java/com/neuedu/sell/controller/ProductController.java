package com.neuedu.sell.controller;

import com.neuedu.sell.entity.ProductCategory;
import com.neuedu.sell.entity.ProductInfo;
import com.neuedu.sell.service.ProductCategoryService;
import com.neuedu.sell.service.ProductInfoService;
import com.neuedu.sell.utils.ResultVOUtils;
import com.neuedu.sell.vo.ProductCategoryVO;
import com.neuedu.sell.vo.ProductInfoVO;
import com.neuedu.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
public class ProductController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //1.查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        //2.根据商品列表构建一个商品类别编号的集合
        List<Integer> catagoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList) {
            catagoryTypeList.add(productInfo.getCategoryType());
        }
        //3.根据商品类别编号集合查询对应的类别
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(catagoryTypeList);
        //4.数据拼装
        //将原数据集合转化成vo集合
        List<ProductCategoryVO> productCategoryVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            //构建vo对象
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            //将原对象数据赋值到vo对象中，利用Spring提供的BeanUtils的类
            BeanUtils.copyProperties(productCategory,productCategoryVO);

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            //将商品原数据转化成商品vo集合
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    //将商品vo集合设置到类别对象中
                    productInfoVOList.add(productInfoVO);
                }
            }
            //将转化好的商品vo集合设置到类别中
            productCategoryVO.setProductInfoVOList(productInfoVOList);
            //将vo对象放到集合中
            productCategoryVOList.add(productCategoryVO);
        }




        /*ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        //将类别集合放入到结果对象中
        resultVO.setData(productCategoryVOList);*/

        return ResultVOUtils.success(productCategoryVOList);
    }
}
