package com.amitesh.finance_flow.init;



import java.util.Arrays;


import jakarta.annotation.PostConstruct;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.ValidationAction;
import com.mongodb.client.model.ValidationLevel;
import com.mongodb.client.model.ValidationOptions;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;

@Component
public class MongoCollectionInitializer {

    private final MongoClient mongoClient;
    private final String databaseName;

    public MongoCollectionInitializer(MongoClient mongoClient,
                                      @Value("${spring.data.mongodb.database}") String databaseName) {
        this.mongoClient = mongoClient;
        this.databaseName = databaseName;
    }

    @PostConstruct
    public void init() {
        MongoDatabase db = mongoClient.getDatabase(databaseName);
        String collName = "users";

        boolean exists = false;
        for (String name : db.listCollectionNames()) {
            if (name.equals(collName)) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            // Build validator Document programmatically
            Document emailProp = new Document("bsonType", "string").append("description", "email must be a string");
            Document fullNameProp = new Document("bsonType", "string");
            Document countryProp = new Document("bsonType", "string");
            Document currencyProp = new Document("bsonType", "string");
            Document createdAtProp = new Document("bsonType", "date").append("description", "must be a date");

            Document settingsProps = new Document()
                    .append("weekStart", new Document("bsonType", "string"))
                    .append("privacyMode", new Document("bsonType", "bool"));

            Document properties = new Document()
                    .append("email", emailProp)
                    .append("fullName", fullNameProp)
                    .append("country", countryProp)
                    .append("currency", currencyProp)
                    .append("createdAt", createdAtProp)
                    .append("settings", new Document("bsonType", "object").append("properties", settingsProps));

            Document schema = new Document("bsonType", "object")
                    .append("required", Arrays.asList("email", "createdAt"))
                    .append("properties", properties);

            Document validator = new Document("$jsonSchema", schema);

            ValidationOptions validationOptions = new ValidationOptions()
                    .validator(validator)
                    .validationLevel(ValidationLevel.STRICT)
                    .validationAction(ValidationAction.ERROR);

            CreateCollectionOptions collOptions = new CreateCollectionOptions()
                    .validationOptions(validationOptions);

            db.createCollection(collName, collOptions);

            MongoCollection<Document> coll = db.getCollection(collName);
            coll.createIndex(Indexes.ascending("email"), new IndexOptions().unique(true));

            System.out.println("Created 'users' collection with validator and unique index on email");
        } else {
            System.out.println("'users' collection already exists - skipping creation");
            // Optional: you can modify validator later using collMod (shown below)
        }
    }

}
