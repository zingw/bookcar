package com.java.backend.service;

import com.java.backend.dto.response.AuthorityResponse;

import java.util.List;

public interface SystemService {
    List<AuthorityResponse> getAllAuthorities();
}
