package com.java.backend.repository;

import com.java.backend.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryCustom {

    Page<UserResponse> findByDeletedFalse(Pageable pageable);
}
