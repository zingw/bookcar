package com.java.backend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "role")
public class Role extends Auditor {
    @Id
    private String id;
    private String name;
}
