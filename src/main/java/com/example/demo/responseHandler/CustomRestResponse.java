package com.example.demo.responseHandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomRestResponse {

    @JsonView(Views.DataView.class)
    private int status;

    @JsonView(Views.DataView.class)
    private String message;

    @JsonView(Views.DataView.class)
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:s a")
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:s a")
    private LocalDateTime timeStamp;

    @JsonView(Views.DataView.class)
    private Object data;

    @JsonView(Views.PageableView.class)
    private int totalPages;

    @JsonView(Views.PageableView.class)
    private long totalElements;

    @JsonView(Views.PageableView.class)
    private boolean last;

    @JsonView(Views.PageableView.class)
    private int pageSize;

    @JsonView(Views.PageableView.class)
    private int pageNumber;

    @JsonView(Views.PageableView.class)
    private boolean first;

    @JsonView(Views.PageableView.class)
    private int numberOfElements;

}