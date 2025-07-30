package com.spring_async.spring_async.router;

import com.spring_async.spring_async.handler.StudentHandler;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class StudentRouter {


    @RouterOperations({
            @RouterOperation(
                    path="/create-student",
                    method= RequestMethod.POST,
                    beanClass = StudentHandler.class,
                    beanMethod = "createStudent"
            ),
            @RouterOperation(
                    path="/fetch-student",
                    method= RequestMethod.GET,
                    beanClass = StudentHandler.class,
                    beanMethod = "fetchStudent"
            ),
            @RouterOperation(
                    path="/update-student",
                    method= RequestMethod.PUT,
                    beanClass = StudentHandler.class,
                    beanMethod = "updateStudent"
            ),
            @RouterOperation(
                    path="/fetch-all-student",
                    method= RequestMethod.GET,
                    beanClass = StudentHandler.class,
                    beanMethod = "fetchAllStudents"
            ),
            @RouterOperation(
                    path="/delete-student",
                    method= RequestMethod.DELETE,
                    beanClass = StudentHandler.class,
                    beanMethod = "deleteStudent"
            ),
            @RouterOperation(
                    path = "/save-all-students",
                    method = RequestMethod.POST,
                    beanClass = StudentHandler.class,
                    beanMethod = "saveAllStudents"
            )
    })

    @Bean
    public RouterFunction<ServerResponse> studentRoutes(StudentHandler studentHandler) {
        return RouterFunctions.route()
                .POST("/create-student", studentHandler::createStudent)
                .POST("/save-all-students", studentHandler::saveAllStudents)
                .GET("/fetch-student", studentHandler::fetchStudent)
                .PUT("/update-student", studentHandler::updateStudent)
                .GET("/fetch-all-student", studentHandler::fetchAllStudents)
                .DELETE("/delete-student", studentHandler::deleteStudent)
                .build();
    }

}

