package com.kansal.mongoupsert.service;

import com.kansal.mongoupsert.model.UpsertRequest;
import com.kansal.mongoupsert.model.User;
import com.kansal.mongoupsert.repo.UpsertMongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpsertService {

    @Autowired
    private UpsertMongoTemplate mongoTemplate;

    public User upsertUser(UpsertRequest request, String userId, String cft) {
        return mongoTemplate.addUser(request.getUserName(), request.getDomain(), userId, cft, 5);
    }
}
