package com.atguigu.mongo;

import com.atguigu.mongo.model.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class MongoTemplateTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    //添加
    @Test
    public void add() {
        User user = new User();
        user.setName("action");
        user.setAge(20);
        user.setCreateDate(new Date());
        mongoTemplate.insert(user);
    }

    //查询所有
    @Test
    public void findAll() {
        List<User> list = mongoTemplate.findAll(User.class);
        list.forEach(user->{
            System.out.println(user);
        });
    }

    //根据id查询
    @Test
    public void testFindId() {
        User user = mongoTemplate.findById("666a9b5e9a3653796627bb3c", User.class);
        System.out.println(user);
    }

    //条件查询
    @Test
    public void testCondition() {
        // where name=? and age=?
        Criteria criteria =
                Criteria.where("name").is("test").and("age").is(20);
        Query query = new Query(criteria);

        List<User> list = mongoTemplate.find(query,User.class);
        System.out.println(list);
    }

    //分页查询
    @Test
    public void testPage() {
        // limit 0,2
        Query query = new Query();
        List<User> list = mongoTemplate.find(query.skip(0).limit(2), User.class);
        list.forEach(user->{
            System.out.println(user);
        });
    }

    //修改和删除
    //修改
    @Test
    public void testUpdateUser() {
        Criteria criteria = Criteria.where("_id").is("64eeeae31711344f35635788");
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("name", "zhangsan");
        update.set("age", 99);
        UpdateResult result = mongoTemplate.upsert(query, update, User.class);//改一条
        //UpdateResult result = mongoTemplate.updateMulti(query, update, User.class);//改多条
        long count = result.getModifiedCount();
        System.out.println(count);
    }

    //删除
    @Test
    public void testRemove() {
        Criteria criteria = Criteria.where("_id").is("64eeeae31711344f35635788");
        Query query = new Query(criteria);
        DeleteResult result = mongoTemplate.remove(query, User.class);
        long count = result.getDeletedCount();
        System.out.println(count);
    }
}