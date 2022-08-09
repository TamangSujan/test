package com.ayata.test.repo;

import com.ayata.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseRepo extends JpaRepository<User, Integer> {

}
