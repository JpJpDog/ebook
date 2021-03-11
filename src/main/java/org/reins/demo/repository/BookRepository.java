package org.reins.demo.repository;

import org.reins.demo.entity.BookE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookE, Integer> {
    List<BookE> findAllByNameContaining(String name);
}
