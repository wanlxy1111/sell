package com.neuedu.sell.service.impl;

import com.neuedu.sell.dto.OrderDTO;
import com.neuedu.sell.enums.ResultEnum;
import com.neuedu.sell.exception.SellException;
import com.neuedu.sell.service.BuyerService;
import com.neuedu.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String orderId, String openid) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO cancelOrder(String orderId, String openid) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(orderId);
        OrderDTO resultDTO = orderService.cancel(orderDTO);
        if (!resultDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        return resultDTO;
    }
}
