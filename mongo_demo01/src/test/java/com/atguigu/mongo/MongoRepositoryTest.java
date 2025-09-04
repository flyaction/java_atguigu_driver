package com.atguigu.mongo;

import com.atguigu.mongo.model.User;
import com.atguigu.mongo.repository.UserRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author: action
 * @create: 2025/9/3 17:13
 **/
@SpringBootTest
public class MongoRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    //添加
    @Test
    public void add() {
        User user = new User();
        user.setName("mary");
        user.setAge(30);
        user.setCreateDate(new Date());
        userRepository.save(user);
    }

    //查询所有
    @Test
    public void findAll() {
        List<User> list = userRepository.findAll();
        System.out.println(list);
    }

    //根据id查询
    @Test
    public void testFindById() {
        Optional<User> optional =
                userRepository.findById(new ObjectId("68b807361e43052c95e87ede"));
        boolean present = optional.isPresent();
        if(present) {
            User user = optional.get();
            System.out.println(user);
        }
    }

    //条件查询 + 排序
    // age = 20
    @Test
    public void testFindCondition() {
        //封装条件
        User user = new User();
        user.setAge(20);
        Example<User> example = Example.of(user);

        Sort sort = Sort.by(Sort.Direction.DESC, "name");

        List<User> list = userRepository.findAll(example, sort);
        System.out.println(list);
    }

    //分页查询
    @Test
    public void testPage() {
        //第一页从0开始的
        PageRequest pageable = PageRequest.of(0,2);

        Page<User> page = userRepository.findAll(pageable);

        List<User> list = page.getContent();
        System.out.println(list);
    }

    //更新
    @Test
    public void testUpdateUser(){
        //注意：先查询，再更新
        Optional<User> optional = userRepository.findById(
                new ObjectId("68b807361e43052c95e87ede")
        );
        if(optional.isPresent()){
            User user = optional.get();
            user.setAge(100);
            //user中包含id，就会执行更新
            userRepository.save(user);
            System.out.println(user);
        }
    }

    //删除
    @Test
    public void testDeleteUser(){
        userRepository.deleteById(
                new ObjectId("64eee9dff317c823c62b4faf")
        );
    }

}
