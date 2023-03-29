package com.java.backend.repository;

import com.java.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom{
    Optional<User> findByUsername(String username);
    boolean existsByUsernameOrEmailOrPhoneNumberAndActivatedTrue(String username, String email, String phoneNumber);
}
