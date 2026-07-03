package org.example.repo;

import org.example.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserModelRepo extends MongoRepository<UserModel , String> {

//    boolean existsbyEmail(String email);
    UserModel findByEmail(String email);
    boolean existsByEmail(String email);
}
