package com.java.backend.repository.impl;

import com.java.backend.dto.response.UserResponse;
import com.java.backend.repository.UserRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private static final String DELETED_FIELD = "deleted";
    private final MongoTemplate mongoTemplate;

    @Override
    public Page<UserResponse> findByDeletedFalse(Pageable pageable) {

        Criteria criteria = Criteria.where(DELETED_FIELD).is(false);
        Query query = new Query();
        query.with(pageable);
        query.addCriteria(criteria);
        List<UserResponse> users = mongoTemplate.find(query, UserResponse.class, "user");
        long count = mongoTemplate.count(query, UserResponse.class, "user");

        return new PageImpl<>(users, pageable, count);
    }
}
