package com.java.backend.repository;

import com.java.backend.entity.PendingRegister;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PendingRegisterRepository extends MongoRepository<PendingRegister, String> {}
