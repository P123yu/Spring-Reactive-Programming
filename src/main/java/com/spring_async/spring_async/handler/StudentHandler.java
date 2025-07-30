package com.spring_async.spring_async.handler;
//
//public class StudentHandler {
//}



import com.spring_async.spring_async.model.Student;
import com.spring_async.spring_async.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StudentHandler {

    private final StudentService studentService;

    @Operation(
            summary = "create student",
            description = "create a new student",
            requestBody = @RequestBody(
                    description="create student",
                    required = true,
                    content= @Content(
                            schema=@Schema(implementation = Student.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            content = @Content(
                                    schema = @Schema(implementation = Student.class)
                            )
                    )}
    )

    public Mono<ServerResponse> createStudent(ServerRequest request) {
        return request.bodyToMono(Student.class)
                .flatMap(studentService::createStudent)
                .flatMap(savedStudent -> ServerResponse.ok().bodyValue(savedStudent));
    }



}
