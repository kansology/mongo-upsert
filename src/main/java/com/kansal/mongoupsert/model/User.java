package com.kansal.mongoupsert.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class User {
    private String domain;
    private String userName;
    private String status;
    private List<Activity> activities;
    private Audit audit;
}
