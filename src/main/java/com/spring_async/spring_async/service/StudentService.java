package com.spring_async.spring_async.service;

import com.spring_async.spring_async.model.Student;
import com.spring_async.spring_async.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface StudentService {

    Mono<Student> createStudent(Student student);

}
