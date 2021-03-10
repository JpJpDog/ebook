package org.reins.demo.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;


@Data
@Document(collection = "bookData")
public class BookMongo {
    @Id
    @Field("_id")
    private ObjectId id;

    @Field("bookId")
    private Integer bookId;

    @Field("description")
    private String description;
}
