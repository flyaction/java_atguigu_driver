package com.atguigu.mongo.repository;

import com.atguigu.mongo.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: action
 * @create: 2025/9/3 17:11
 **/
public interface UserRepository extends MongoRepository<User, ObjectId> {
}
