package com.ayata.test.repo;

import com.ayata.test.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatabaseRepo extends JpaRepository<Employee, Integer> {

}
