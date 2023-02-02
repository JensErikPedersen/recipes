package dk.serik.recipes.exceptions;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class BusinessException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String description;
    private HttpStatus httpStatus;

    public BusinessException(ClientError type) {
        super(type.getDescription());
        if (type.getCode() == 100) this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        else this.httpStatus = HttpStatus.BAD_REQUEST;
        this.code = type.getCode();
        this.description = type.getDescription();
    }

    public BusinessException(ClientError type, HttpStatus status) {
        this(type);
        this.httpStatus = status;  // overwritting HttpStatus
    }

    public BusinessException(ClientError type, String description) {
        this(type);
        this.description = description;  // overwritting description
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }


    public int getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", httpStatus=" + httpStatus +
                '}';
    }
}
