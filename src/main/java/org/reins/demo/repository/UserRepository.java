package org.reins.demo.repository;

import org.reins.demo.entity.UserE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserE, Integer> {
}
