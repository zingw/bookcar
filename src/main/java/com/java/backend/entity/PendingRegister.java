package com.java.backend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "pending_register")
public class PendingRegister {

    @Id
    private String id;

    private String userId;

    public PendingRegister(String userId) {
        this.userId = userId;
    }
}
