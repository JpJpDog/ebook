package org.reins.demo.repository;

import org.reins.demo.entity.BookMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookMongoRepository extends MongoRepository<BookMongo, Integer> {
    Optional<BookMongo> findByBookId(Integer bookId);
}
