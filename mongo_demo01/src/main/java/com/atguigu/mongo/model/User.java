package com.atguigu.mongo.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author: action
 * @create: 2025/9/3 16:47
 **/
@Data
@Document("user") //指定mongodb中的集合名字
public class User {

    @Id
    private ObjectId id;

    private String name;
    private Integer age;
    private String email;
    private Date createDate;
}
