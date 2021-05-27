package org.reins.demo.repository;

import org.reins.demo.entity.BookMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookMongoRepository extends MongoRepository<BookMongo, Integer> {
    List<BookMongo> findByBookId(Integer bookId);
}
