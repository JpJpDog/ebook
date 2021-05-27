package org.reins.demo.repository;

import org.reins.demo.entity.UserE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserE, Integer> {
    List<UserE> findByName(String name);
}
