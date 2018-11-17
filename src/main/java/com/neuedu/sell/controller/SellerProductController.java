package com.neuedu.sell.controller;

import com.neuedu.sell.entity.ProductCategory;
import com.neuedu.sell.entity.ProductInfo;
import com.neuedu.sell.enums.ProductStatusEnum;
import com.neuedu.sell.enums.ResultEnum;
import com.neuedu.sell.exception.SellException;
import com.neuedu.sell.form.ProductForm;
import com.neuedu.sell.service.ProductCategoryService;
import com.neuedu.sell.service.ProductInfoService;
import com.neuedu.sell.utils.KeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size){
        PageRequest request = new PageRequest(page - 1,size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(request);
        ModelAndView model = new ModelAndView("product/list");
        model.addObject("productInfoPage",productInfoPage);
        model.addObject("currentPage",page);
        model.addObject("size",size);
        return model;
    }

    @GetMapping("/off_sale")
    public ModelAndView productDown(@RequestParam(value = "productId") String productId){
        try {
            ProductInfo productInfo = productInfoService.findOne(productId);
            productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
            productInfoService.save(productInfo);
        }catch (SellException e){
            ModelAndView errorModel = new ModelAndView("common/error");
            errorModel.addObject("msg",e.getMessage());
            errorModel.addObject("url","/sell/seller/product/list");
            return errorModel;
        }
        ModelAndView model = new ModelAndView("common/success");
        model.addObject("msg",ResultEnum.OFF_SALE_SUCCESS.getMessage());
        model.addObject("url","/sell/seller/product/list");
        return model;
    }

    @GetMapping("/on_sale")
    public ModelAndView productUp(@RequestParam(value = "productId") String productId){
        try {
            ProductInfo productInfo = productInfoService.findOne(productId);
            productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
            productInfoService.save(productInfo);
        }catch (SellException e){
            ModelAndView errorModel = new ModelAndView("common/error");
            errorModel.addObject("msg",e.getMessage());
            errorModel.addObject("url","/sell/seller/product/list");
            return errorModel;
        }
        ModelAndView model = new ModelAndView("common/success");
        model.addObject("msg",ResultEnum.ON_SALE_SUCCESS.getMessage());
        model.addObject("url","/sell/seller/product/list");
        return model;
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId){
        ModelAndView model = new ModelAndView("product/index");
        if (!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productInfoService.findOne(productId);
            model.addObject("productInfo",productInfo);
        }
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        model.addObject("productCategoryList",productCategoryList);
        return model;
    }

    @PostMapping("/save")
    public ModelAndView save(ProductForm productForm){
        //判断新增还是修改
        if (StringUtils.isEmpty(productForm.getProductId())){
            //新增
            ProductInfo productInfo = new ProductInfo();
            BeanUtils.copyProperties(productForm,productInfo);
            productInfo.setProductId(KeyUtils.generateUniqueKey());
            productInfoService.save(productInfo);
        }else {
            //修改
            ProductInfo productInfo = productInfoService.findOne(productForm.getProductId());
            BeanUtils.copyProperties(productForm,productInfo);
            productInfoService.save(productInfo);
        }
        ModelAndView model = new ModelAndView("redirect:list");
        return model;
    }
}
