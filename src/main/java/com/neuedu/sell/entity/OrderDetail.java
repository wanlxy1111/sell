package com.neuedu.sell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 订单详情表
 */

@Data
@Entity
@DynamicUpdate
public class OrderDetail {

    @Id
    private String detailId;
    //订单id
    private String orderId;
    //商品id
    private String productId;
    //商品名
    private String productName;
    //单价
    private BigDecimal productPrice;
    //数量
    private Integer productQuantity;
    //图
    private String productIcon;

    public OrderDetail() {
    }

    public OrderDetail(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
