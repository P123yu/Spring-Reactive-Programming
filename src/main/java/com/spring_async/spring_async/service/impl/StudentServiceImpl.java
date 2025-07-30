package com.spring_async.spring_async.service.impl;

import com.spring_async.spring_async.dto.Response;
import com.spring_async.spring_async.dto.StudentDto;
import com.spring_async.spring_async.model.Student;
import com.spring_async.spring_async.repo.StudentRepo;
import com.spring_async.spring_async.service.StudentService;
import com.spring_async.spring_async.utility.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;


    @Override
    public Mono<Response> createStudent(Student student) {
        return studentRepo.save(student).map(studentTemplate -> {
            Response response = new Response();
            response.setData(studentTemplate.getId());
            response.setStatus(Constant.SUCCESS);
            response.setMessage(Constant.SUCCESS_UPDATE);
            return response;
        }).onErrorResume(error -> {
            Response response = new Response();
            response.setStatus(Constant.ERROR);
            response.setMessage(Constant.TRY_AGAIN_LATER);
            response.setErrorText(error.getMessage());
            return Mono.just(response);
        });
    }

    @Override
    public Mono<Response> findStudentById(Long id) {
        return studentRepo.findById(id).map(studentTemplate -> {
                    StudentDto dto = new StudentDto();
                    dto.setId(studentTemplate.getId());
                    dto.setName(studentTemplate.getName());
                    dto.setCity(studentTemplate.getCity());
                    Response response = new Response();
                    response.setStatus(Constant.SUCCESS);
                    response.setData(dto);
                    return response;
                })
                .switchIfEmpty(Mono.fromSupplier(() -> {
                    Response response = new Response();
                    response.setStatus(Constant.ERROR);
                    response.setMessage("Student template not found with id " + id);
                    return response;
                }))
                .onErrorResume(error -> {
                    Response response = new Response();
                    response.setStatus(Constant.ERROR);
                    response.setMessage(Constant.TRY_AGAIN_LATER);
                    response.setErrorText(error.getMessage());
                    return Mono.just(response);
                });
    }




    @Override
    public Mono<Response> updateStudent(Student student) {
        return studentRepo.findById(student.getId())
                .flatMap(existing -> {
                    existing.setName(student.getName());
                    existing.setCity(student.getCity());
                    return studentRepo.save(existing);
                })
                .map(updated -> {
                    Response response = new Response();
                    response.setStatus(Constant.SUCCESS);
                    response.setMessage(Constant.SUCCESS_UPDATE);
                    response.setData(updated.getId());
                    return response;
                })
                .switchIfEmpty(Mono.fromSupplier(() -> {
                    Response response = new Response();
                    response.setStatus(Constant.ERROR);
                    response.setMessage("No student found with id " + student.getId());
                    return response;
                }))
                .onErrorResume(error -> {
                    Response response = new Response();
                    response.setStatus(Constant.ERROR);
                    response.setMessage(Constant.TRY_AGAIN_LATER);
                    response.setErrorText(error.getMessage());
                    return Mono.just(response);
                });
    }

    @Override
    public Mono<Response> getAllStudents() {
        return studentRepo.findAll()
                .collectList() // collect all students into a List<Student>
                .map(studentList -> {
                    Response response = new Response();
                    response.setStatus(Constant.SUCCESS);
                    response.setData(studentList); // set entire list as data
                    return response;
                })
                .onErrorResume(error -> {
                    Response response = new Response();
                    response.setStatus(Constant.ERROR);
                    response.setMessage(Constant.TRY_AGAIN_LATER);
                    response.setErrorText(error.getMessage());
                    return Mono.just(response);
                });
    }


    @Override
    public Mono<Response> deleteStudent(Long id) {
        return studentRepo.findById(id)
                .flatMap(existing -> studentRepo.deleteById(id)
                        .then(Mono.fromSupplier(() -> {
                            Response response = new Response();
                            response.setStatus(Constant.SUCCESS);
                            response.setMessage("Deleted student with id " + id);
                            return response;
                        })))
                .switchIfEmpty(Mono.fromSupplier(() -> {
                    Response response = new Response();
                    response.setStatus(Constant.ERROR);
                    response.setMessage("No student found with id " + id);
                    return response;
                }))
                .onErrorResume(error -> {
                    Response response = new Response();
                    response.setStatus(Constant.ERROR);
                    response.setMessage(Constant.TRY_AGAIN_LATER);
                    response.setErrorText(error.getMessage());
                    return Mono.just(response);
                });
    }


    @Override
    public Mono<Response> saveAllStudents(Flux<Student> students) {
        return studentRepo.saveAll(students)
                .collectList()
                .map(savedList -> {
                    Response response = new Response();
                    response.setStatus(Constant.SUCCESS);
                    response.setMessage("Students saved successfully");
                    response.setData(savedList);
                    return response;
                })
                .onErrorResume(e -> {
                    Response response = new Response();
                    response.setStatus(Constant.ERROR);
                    response.setMessage(Constant.TRY_AGAIN_LATER);
                    response.setErrorText(e.getMessage());
                    return Mono.just(response);
                });
    }


}
