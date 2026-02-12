package org.techmate.techmate_be.model.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class GenericResponse <T> {
    private T data;
    private String message;

    public GenericResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseEntity<GenericResponse<T>> withData(String message, int status, T data) {
        return ResponseEntity.status(status).body(new GenericResponse<>(message, data));
    }

    public static <T> ResponseEntity<GenericResponse<T>> withMessage(String message, int status) {
        return ResponseEntity.status(status).body(new GenericResponse<>(message, null));
    }
}