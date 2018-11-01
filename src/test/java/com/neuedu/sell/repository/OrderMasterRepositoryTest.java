package com.neuedu.sell.repository;

import com.neuedu.sell.entity.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    public static final String OPENID = "abc123";

    @Test
    public void saveTest(){
        /*OrderMaster orderMaster = new OrderMaster();*/
        OrderMaster orderMaster = repository.findOne("123456789");
        orderMaster.setOrderId("123456789");
        orderMaster.setBuyerName("张三");
        orderMaster.setBuyerAddress("系霸道");
        orderMaster.setBuyerPhone("15333333333");
        orderMaster.setOrderAmount(new BigDecimal(40));
        orderMaster.setBuyerOpenid(OPENID);
        repository.save(orderMaster);
    }

    @Test
    public void findByBuyerOpenid(){
        Page<OrderMaster> page = repository.findByBuyerOpenid("abc123",new PageRequest(0,2));
        for (OrderMaster orderMaster : page.getContent()) {
            System.out.println(orderMaster);
        }
    }

}