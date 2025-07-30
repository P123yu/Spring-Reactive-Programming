package com.spring_async.spring_async.service;

import com.spring_async.spring_async.dto.Response;
import com.spring_async.spring_async.model.Student;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface StudentService {

    Mono<Response> createStudent(Student student);

    Mono<Response> findStudentById(Long id);

    Mono<Response> updateStudent(Student student);
    Mono<Response> getAllStudents();
    Mono<Response> deleteStudent(Long id);

    Mono<Response> saveAllStudents(Flux<Student> students);



}
