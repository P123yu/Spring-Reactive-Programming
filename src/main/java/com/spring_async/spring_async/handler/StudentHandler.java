package com.spring_async.spring_async.handler;

import com.spring_async.spring_async.dto.StudentDto;
import com.spring_async.spring_async.model.Student;
import com.spring_async.spring_async.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class StudentHandler {

    private final StudentService studentService;

    @Operation(
            summary = "Create student",
            description = "Create a new student",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(schema = @Schema(implementation = Student.class))
            ),
            responses = {
                    @ApiResponse(
                            description = "Student created",
                            content = @Content(schema = @Schema(implementation = Student.class))
                    )
            }
    )
    public Mono<ServerResponse> createStudent(ServerRequest request) {
        return request.bodyToMono(Student.class)
                .flatMap(studentService::createStudent)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    @Operation(
            summary = "Fetch student",
            description = "Fetch student by ID",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "id", description = "Student ID", required = true)
            },
            responses = {
                    @ApiResponse(
                            description = "Student fetched",
                            content = @Content(schema = @Schema(implementation = StudentDto.class))
                    )
            }
    )
    public Mono<ServerResponse> fetchStudent(ServerRequest request) {
        return Mono.justOrEmpty(request.queryParam("id"))
                .map(Long::parseLong)
                .flatMap(id -> studentService.findStudentById(id)
                        .flatMap(response -> ServerResponse.ok().bodyValue(response)));
    }

    @Operation(
            summary = "Update student",
            description = "Update an existing student",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(schema = @Schema(implementation = Student.class))
            ),
            responses = {
                    @ApiResponse(
                            description = "Student updated",
                            content = @Content(schema = @Schema(implementation = Student.class))
                    )
            }
    )
    public Mono<ServerResponse> updateStudent(ServerRequest request) {
        return request.bodyToMono(Student.class)
                .flatMap(studentService::updateStudent)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    @Operation(
            summary = "Fetch all students",
            description = "Retrieve all students",
            responses = {
                    @ApiResponse(
                            description = "List of students",
                            content = @Content(schema = @Schema(implementation = StudentDto.class))
                    )
            }
    )

    public Mono<ServerResponse> fetchAllStudents(ServerRequest request) {
        return studentService.getAllStudents()
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }


    @Operation(
            summary = "Delete student",
            description = "Delete student by ID",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "id", description = "Student ID", required = true)
            },
            responses = {
                    @ApiResponse(
                            description = "Student deleted"
                    )
            }
    )
    public Mono<ServerResponse> deleteStudent(ServerRequest request) {
        return Mono.justOrEmpty(request.queryParam("id"))
                .map(Long::parseLong)
                .flatMap(id -> studentService.deleteStudent(id)
                        .flatMap(response -> ServerResponse.ok().bodyValue(response)));
    }



    @Operation(
            summary = "Save multiple students",
            description = "Save a list of students",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Student.class)))
            ),
            responses = {
                    @ApiResponse(
                            description = "Students saved",
                            content = @Content(schema = @Schema(implementation = Student.class))
                    )
            }
    )
    public Mono<ServerResponse> saveAllStudents(ServerRequest request) {
        return studentService.saveAllStudents(request.bodyToFlux(Student.class))
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

}