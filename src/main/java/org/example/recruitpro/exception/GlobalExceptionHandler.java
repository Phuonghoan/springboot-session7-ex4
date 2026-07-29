package org.example.recruitpro.exception;

import org.example.recruitpro.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * Validation lỗi khi dùng @RequestBody.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>>
    handleRequestBodyValidation(
            MethodArgumentNotValidException exception
    ) {
        return buildValidationResponse(
                exception.getBindingResult()
        );
    }

    /*
     * Validation hoặc binding lỗi khi dùng @ModelAttribute.
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>>
    handleFormValidation(
            BindException exception
    ) {
        return buildValidationResponse(
                exception.getBindingResult()
        );
    }

    /*
     * Không tìm thấy Candidate.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>>
    handleResourceNotFound(
            ResourceNotFoundException exception
    ) {
        ApiResponse<Void> response =
                ApiResponse.of(
                        "404 NOT_FOUND",
                        exception.getMessage(),
                        null
                );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    private ResponseEntity<
            ApiResponse<Map<String, String>>
            > buildValidationResponse(
            BindingResult bindingResult
    ) {
        Map<String, String> errors =
                new LinkedHashMap<>();

        bindingResult.getFieldErrors()
                .forEach(fieldError ->
                        errors.putIfAbsent(
                                fieldError.getField(),
                                fieldError.getDefaultMessage()
                        )
                );

        ApiResponse<Map<String, String>> response =
                ApiResponse.of(
                        "400 BAD_REQUEST",
                        "Dữ liệu không hợp lệ",
                        errors
                );

        return ResponseEntity
                .badRequest()
                .body(response);
    }
}