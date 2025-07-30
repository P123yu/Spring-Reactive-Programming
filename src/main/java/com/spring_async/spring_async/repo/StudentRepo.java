package com.spring_async.spring_async.repo;

import com.spring_async.spring_async.model.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends ReactiveCrudRepository<Student,Long> {
}
