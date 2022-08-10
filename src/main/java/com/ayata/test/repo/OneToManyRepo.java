package com.ayata.test.repo;

import com.ayata.test.model.Comments;
import com.ayata.test.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OneToManyRepo extends JpaRepository<Comments, Integer> {
}
