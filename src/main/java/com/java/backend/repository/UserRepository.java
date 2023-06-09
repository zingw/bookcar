package com.java.backend.repository;

import com.java.backend.entity.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    boolean existsByUsernameOrEmailOrPhoneNumberAndActivatedTrue(String username, String email, String phoneNumber);

    Page<User> findByDeletedFalse(Pageable pageable);
}
