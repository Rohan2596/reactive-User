package com.bridgelabz.reactiveuserservice.repository;

import com.bridgelabz.reactiveuserservice.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User,String> {


  Optional <Mono<User>> findByEmailId(String emailId);
}
