package com.atguigu.drools;

import com.atguigu.drools.bean.Integral;
import com.atguigu.drools.bean.Order;
import com.atguigu.drools.bean.Order1;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: action
 * @create: 2025/8/15 09:37
 **/
@SpringBootTest
public class DroolsDemosApplicationTests {

    @Autowired
    private KieContainer kieContainer;

    @Test
    public void test(){
        //从Kie容器对象中获取会话对象
        KieSession session = kieContainer.newKieSession();

        //Fact对象，事实对象
        Order order = new Order();
        order.setAmout(300);

        //将Order对象插入到工作内存中
        session.insert(order);

        //激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();
        //关闭会话
        session.dispose();

        System.out.println("订单金额：" + order.getAmout() +
                "，添加积分：" + order.getScore());
    }

    @Test
    public void test2(){
        //从Kie容器对象中获取会话对象
        KieSession session = kieContainer.newKieSession();

        //Fact对象，事实对象
        Order1 order = new Order1();
        order.setAmout(30);

        //全局变量
        Integral integral = new Integral();
        session.setGlobal("integral",integral);

        //将Order对象插入到工作内存中
        session.insert(order);

        //激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();
        //关闭会话
        session.dispose();

        System.out.println("订单金额：" + order.getAmout() +
                "，添加积分：" + integral.getScore());
    }

}
