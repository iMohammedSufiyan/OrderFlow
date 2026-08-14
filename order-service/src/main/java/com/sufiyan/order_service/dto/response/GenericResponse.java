package com.sufiyan.order_service.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenericResponse<T> {
    boolean success;
    String message;
    T data;
    int statusCode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime timestamp = LocalDateTime.now();

    Map<String, String> errors;

    // Constructors
    public GenericResponse() {
        this.errors = new HashMap<>();
    }

    public GenericResponse(boolean success, String message, T data, int statusCode) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
        this.errors = new HashMap<>();
    }

    // Static factory methods for convenience
    public static <T> GenericResponse<T> success(T data) {
        return new GenericResponse<>(true, "Success", data, 200);
    }

    public static <T> GenericResponse<T> success(String message, T data) {
        return new GenericResponse<>(true, message, data, 200);
    }

    public static <T> GenericResponse<T> error(String message) {
        return new GenericResponse<>(false, message, null, 500);
    }

    public static <T> GenericResponse<T> error(String message, int statusCode) {
        return new GenericResponse<>(false, message, null, statusCode);
    }

    public static <T> GenericResponse<T> notFound(String message) {
        return new GenericResponse<>(false, message, null, 404);
    }

    public static <T> GenericResponse<T> badRequest(String message) {
        return new GenericResponse<>(false, message, null, 400);
    }

    public void addError(String field, String errorMessage) {
        this.errors.put(field, errorMessage);
    }

    // Utility method to convert to Map (e.g., for JSON serialization)
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("success", success);
        map.put("message", message);
        map.put("data", data);
        map.put("statusCode", statusCode);
        if (!errors.isEmpty()) {
            map.put("errors", errors);
        }
        return map;
    }

}
