package com.bridgelabz.reactiveuserservice.repository;

import com.bridgelabz.reactiveuserservice.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User,String> {
}
