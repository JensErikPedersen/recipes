package dk.serik.recipes.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(value = { BusinessException.class })
    @ResponseBody
    public ResponseEntity<ExceptionEnvelope> handleBusinessException(BusinessException ex, WebRequest request) {
        logger.info("Handler BusinessException: " + ex);
        ExceptionEnvelope exceptionEnvelope = new ExceptionEnvelope().setMessage(ex.getMessage()).setErrorCode(ex.getCode());
        return new ResponseEntity<>(exceptionEnvelope, ex.getHttpStatus());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = { Exception.class })
    @ResponseBody
    public ExceptionEnvelope handleGeneralException(Exception ex) {
        ClientError error = ClientError.UNHANDLED_EXCEPTION;
        ExceptionEnvelope exceptionEnvelope = new ExceptionEnvelope().setMessage(error.getDescription()).setErrorCode(error.getCode());
        return exceptionEnvelope;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { javax.validation.ConstraintViolationException.class })
    @ResponseBody
    public ExceptionEnvelope handleConstraintViolationException(javax.validation.ConstraintViolationException ex) {
        logger.info("handle ConstraintViolationException: " + ex);
        ExceptionEnvelope exceptionEnvelope = new ExceptionEnvelope(100, "Validation Exception",
                "One or more fields, parameters or type do not pass violation criteria");
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
