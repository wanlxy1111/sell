package com.neuedu.sell.dto;

import com.neuedu.sell.entity.OrderDetail;
import com.neuedu.sell.enums.OrderStatusEnum;
import com.neuedu.sell.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    //买家名字
    private String buyerName;
    //买家电话
    private String buyerPhone;
    //收货地址
    private String buyerAddress;
    //用户的openid
    private String buyerOpenid;
    //订单总金额
    private BigDecimal orderAmount;
    //订单状态，0为新下单
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    //支付状态，0未支付，1已支付
    private Integer payStatus = PayStatusEnum.NOT_PAY.getCode();
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
    //订单详情的集合
    private List<OrderDetail> orderDetailList;
}
