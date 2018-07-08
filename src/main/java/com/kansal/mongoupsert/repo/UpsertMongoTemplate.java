package com.kansal.mongoupsert.repo;

import com.kansal.mongoupsert.model.Activity;
import com.kansal.mongoupsert.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UpsertMongoTemplate {

    @Autowired
    private MongoTemplate mongoTemplate;

    public User addUser(String userName, String domain, String userId, String cft, int loginThreshold) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        query.addCriteria(Criteria.where("domain").is(domain));

        Update update = new Update();
        update.set("audit.dateModified", new Date());
        update.set("audit.userModified", userId);
        update.set("audit.applicationModified", cft);
        update.push("activities").atPosition(0).slice(loginThreshold + 1).value(getActivity());

        update.setOnInsert("userName", userName);
        update.setOnInsert("domain", domain);
        update.setOnInsert("status", "ACTIVE");
        update.setOnInsert("audit.dateCreated", new Date());
        update.setOnInsert("audit.userCreated", userId);
        update.setOnInsert("audit.applicationCreated", cft);
        User upsertedUser = mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true).upsert(true), User.class);

        if(upsertedUser.getActivities().size() >= loginThreshold) {
            update = new Update();
            update.set("status", "LOCKED");
            return mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true).upsert(false), User.class);
        } else {
        return upsertedUser;
        }
    }

    private Activity getActivity() {
        return Activity.builder()
                .attemptDate(new Date())
                .attemptResult("FAILURE")
                .build();
    }
}
