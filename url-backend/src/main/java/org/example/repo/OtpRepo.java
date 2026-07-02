package org.example.repo;


import org.example.models.OTPModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepo extends MongoRepository<OTPModel,String> {
    boolean existsByEmail(String email);
    OTPModel findByEmail(String email);
    void deleteByEmail(String email);

}
