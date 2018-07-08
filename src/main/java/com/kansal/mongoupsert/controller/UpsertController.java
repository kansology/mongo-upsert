package com.kansal.mongoupsert.controller;

import com.kansal.mongoupsert.model.UpsertRequest;
import com.kansal.mongoupsert.model.User;
import com.kansal.mongoupsert.service.UpsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpsertController {

    @Autowired
    private UpsertService service;

    @PostMapping("/upsert")
    public User upsertUser(@RequestBody UpsertRequest request,
                           @RequestHeader("user-id") String userId,
                           @RequestHeader("cft") String cft) {
        return service.upsertUser(request, userId, cft);
    }
}
