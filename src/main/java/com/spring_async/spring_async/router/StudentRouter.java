package com.spring_async.spring_async.router;

import com.spring_async.spring_async.handler.StudentHandler;
import com.spring_async.spring_async.model.Student;
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
            )
    })

    @Bean
    public RouterFunction<ServerResponse> studentRoutes(StudentHandler studentHandler){
        return RouterFunctions.route().POST("/create-student",studentHandler::createStudent).build();
    }


}

