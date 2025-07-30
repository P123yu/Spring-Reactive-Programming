package com.spring_async.spring_async.service.impl;

import com.spring_async.spring_async.model.Student;
import com.spring_async.spring_async.repo.StudentRepo;
import com.spring_async.spring_async.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;


    @Override
    public Mono<Student> createStudent(Student student) {
        return studentRepo.save(student);
    }
}
