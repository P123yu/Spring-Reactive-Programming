package com.spring_async.spring_async.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private String status;
    private String message;
    private String errorText;
    private Object data;
}