package com.example.demo.responseHandler;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public abstract class GlobalResponse {

    private GlobalResponse() {
    }

    public static ResponseEntity<CustomRestResponse> withMessage(String message, HttpStatus httpStatus) {

        CustomRestResponse response = new CustomRestResponse();

        response.setStatus(httpStatus.value());
        response.setMessage(message);
        response.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<CustomRestResponse> withData(String message, Object data, HttpStatus httpStatus) {

        CustomRestResponse response = new CustomRestResponse();

        response.setStatus(httpStatus.value());
        response.setMessage(message);
        response.setData(data);
        response.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<CustomRestResponse> pageable(String message, Page<?> page, HttpStatus httpStatus) {

        CustomRestResponse response = new CustomRestResponse();

        response.setStatus(httpStatus.value());
        response.setMessage(message);
        response.setTimeStamp(LocalDateTime.now());
        response.setData(page.getContent());
        //page data
        response.setTotalPages(page.getTotalPages());
        response.setTotalElements(page.getTotalElements());
        response.setLast(page.isLast());
        response.setPageSize(page.getSize());
        response.setPageNumber(page.getNumber());
        response.setFirst(page.isFirst());
        response.setNumberOfElements(page.getNumberOfElements());

        return new ResponseEntity<>(response, httpStatus);
    }
}