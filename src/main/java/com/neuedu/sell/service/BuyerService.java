package com.neuedu.sell.service;

import com.neuedu.sell.dto.OrderDTO;

public interface BuyerService {

    OrderDTO findOrderOne(String orderId,String openid);

    OrderDTO cancelOrder(String orderId,String openid);
}
