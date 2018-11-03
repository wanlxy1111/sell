package com.neuedu.sell.controller;

import com.neuedu.sell.converter.OrderForm2OrderDTOConverter;
import com.neuedu.sell.dto.OrderDTO;
import com.neuedu.sell.enums.ResultEnum;
import com.neuedu.sell.exception.SellException;
import com.neuedu.sell.form.OrderForm;
import com.neuedu.sell.service.OrderService;
import com.neuedu.sell.utils.ResultVOUtils;
import com.neuedu.sell.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        //1.校验数据的合法性
        if (bindingResult.hasErrors()){
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        //2.转化为OrderDTO类型
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        //3.调用业务层去创建订单
        OrderDTO resultDTO = orderService.create(orderDTO);
        //4.包装结果
        Map<String,String> map = new HashMap<>();
        map.put("orderId",resultDTO.getOrderId());
        //5.返回结果
        return ResultVOUtils.success(map);
    }
}
