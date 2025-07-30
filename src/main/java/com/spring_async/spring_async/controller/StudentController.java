package com.spring_async.spring_async.controller;

import com.spring_async.spring_async.model.Student;
import com.spring_async.spring_async.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public Mono<Student> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }
}
