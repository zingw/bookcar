package com.java.backend.service.impl;

import com.java.backend.dto.response.AuthorityResponse;
import com.java.backend.enums.Authority;
import com.java.backend.enums.GroupAuthority;
import com.java.backend.service.SystemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class SystemServiceImpl implements SystemService {
    @Override
    public List<AuthorityResponse> getAllAuthorities() {

        List<Authority> authorities = Authority.getAllAuthorities();

        Map<GroupAuthority, List<Authority>> map = authorities.stream()
            .collect(groupingBy(Authority::getGroup));

        List<AuthorityResponse> authorityResponses = new ArrayList<>();
        for (GroupAuthority groupAuthority : map.keySet()) {

            String groupName = groupAuthority.name();
            List<AuthorityResponse.Authority> groupedAuthorities = map.get(groupAuthority).stream()
                .map(item -> new AuthorityResponse.Authority(item.name(), item.getVi())).collect(Collectors.toList());

            authorityResponses.add(new AuthorityResponse(groupName, groupedAuthorities));
        }

        return authorityResponses;
    }
}
