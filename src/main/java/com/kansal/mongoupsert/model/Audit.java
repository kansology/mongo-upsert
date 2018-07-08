package com.kansal.mongoupsert.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Audit {
    private Date dateCreated;
    private Date dateModified;
    private String applicationCreated;
    private String userCreated;
    private String userModified;
    private String applicationModified;
}
