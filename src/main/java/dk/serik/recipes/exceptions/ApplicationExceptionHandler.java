package dk.serik.recipes.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@ControllerAdvice
public class ApplicationExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler(value = { ServiceException.class })
    @ResponseBody
    public ResponseEntity<ExceptionEnvelope> handleBusinessException(ServiceException ex, WebRequest request) {
        logger.info("Handler BusinessException: " + ex);
        ExceptionEnvelope exceptionEnvelope = ExceptionEnvelope.builder()
                .message(ex.getMessage())
                .description(ex.getDescription())
                .errorCode(ex.getCode())
                .build();
        return new ResponseEntity<>(exceptionEnvelope, ex.getHttpStatus());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = { Exception.class })
    @ResponseBody
    public ExceptionEnvelope handleGeneralException(Exception ex) {
        ExceptionEnvelope exceptionEnvelope = ExceptionEnvelope.builder()
                .message(ex.getMessage())
                .errorCode(ApplicationErrorCodes.UNHANDLED_EXCEPTION.getCode())
                .build();
        return exceptionEnvelope;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseBody
    public ExceptionEnvelope handleConstraintViolationException(ConstraintViolationException ex) {
        logger.info("handle ConstraintViolationException: " + ex);
        ExceptionEnvelope exceptionEnvelope = ExceptionEnvelope.builder()
                .errorCode(ApplicationErrorCodes.VALIDATION_EXCEPTION.getCode())
                .message("Validation Exception")
                .description("One or more fields, parameters or type do not pass violation criteria")
                .build();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        processFieldErrors(exceptionEnvelope, violations);

        return exceptionEnvelope;
    }

    private void processFieldErrors(ExceptionEnvelope exceptionEnvelope, Set<ConstraintViolation<?>> violations) {
        for (ConstraintViolation<?> violation : violations) {
            ValidationExceptionEnvelope vError = new ValidationExceptionEnvelope();
            if(Objects.nonNull(violation.getPropertyPath())) {
                String fieldName = findFieldViolated(violation.getPropertyPath());
                if(Objects.nonNull(fieldName)) {
                    vError.setObjectName(fieldName);
                } else {
                    vError.setObjectName("Unknown field");
                }
            } else {
                vError.setObjectName(null);
            }

            vError.setMessage(violation.getMessage());
            exceptionEnvelope.addValidationException(vError);
        }
    }


    // utilities

    private String findFieldViolated(Path p) {
        Iterator<Path.Node> it = p.iterator();
        Path.Node n = null;
        while (it.hasNext()) {
            n = it.next();
        }

        if(Objects.nonNull(n)) {
            logger.info("returning: " + n.getName());
            return n.getName();
        } else {
            return null;
        }

    }
}
