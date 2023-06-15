package dk.serik.recipes.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
public class ServiceException extends RuntimeException implements Serializable {
    private String message;
    private int code;
    private String description;
    private HttpStatus httpStatus;

    @Builder
    public ServiceException(String message, int code, String description, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", description='" + description + '\'' +
                ", httpStatus=" + httpStatus +
                '}';
    }
}
