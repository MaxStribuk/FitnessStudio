package by.itacademy.web.util;

import by.itacademy.core.dto.errors.ErrorDto;
import by.itacademy.core.dto.errors.MultipleErrorDto;
import by.itacademy.core.dto.errors.SingleErrorDto;
import by.itacademy.core.exceptions.EntityNotFoundException;
import by.itacademy.core.exceptions.InvalidVersionException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionGlobalHandler {

    private final static String TYPE_ERROR = "error";
    private final static String TYPE_STRUCTURED_ERROR = "structured_error";
    private final static String DEFAULT_USER_ERROR_MESSAGE =
            "The request contains invalid data. Change the request and send it again";
    private final static String DEFAULT_SERVER_ERROR_MESSAGE =
            "The server was unable to process the request correctly. Please contact the administrator";


    @ExceptionHandler()
    public ResponseEntity<MultipleErrorDto> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        List<ErrorDto> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ErrorDto(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        MultipleErrorDto multipleError = new MultipleErrorDto(TYPE_STRUCTURED_ERROR, errors);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(multipleError);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            UnexpectedTypeException.class})
    public ResponseEntity<List<SingleErrorDto>> handleDefaultUserError() {

        SingleErrorDto singleError = new SingleErrorDto(TYPE_ERROR, DEFAULT_USER_ERROR_MESSAGE);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(List.of(singleError));
    }

    @ExceptionHandler
    public ResponseEntity<List<SingleErrorDto>> handlePSQLException(DataIntegrityViolationException e) {
        Throwable rootCause = NestedExceptionUtils.getMostSpecificCause(e);
        String message = rootCause.getMessage();
        SingleErrorDto singleError = new SingleErrorDto(TYPE_ERROR, message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(List.of(singleError));
    }

    @ExceptionHandler()
    public ResponseEntity<MultipleErrorDto> handleConstraintViolationException(
            ConstraintViolationException e
    ) {
        final List<ErrorDto> errors = e.getConstraintViolations()
                .stream()
                .map(
                        error -> new ErrorDto(
                                getField(error.getPropertyPath().toString()),
                                error.getMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MultipleErrorDto(TYPE_STRUCTURED_ERROR, errors));
    }

    @ExceptionHandler({InvalidVersionException.class,
            EntityNotFoundException.class})
    public ResponseEntity<List<SingleErrorDto>> handlePathVariableException(
            InvalidVersionException e
    ) {
        SingleErrorDto error = new SingleErrorDto(TYPE_ERROR, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(List.of(error));
    }

    @ExceptionHandler()
    public ResponseEntity<List<ErrorDto>> handleServerException(RuntimeException e) {
        ErrorDto error = new ErrorDto(TYPE_ERROR, DEFAULT_SERVER_ERROR_MESSAGE);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(error));
    }

    private String getField(String path) {
        if (path.contains(".")) {
            String[] split = path.split("\\.");
            return split[split.length - 1];
        } else {
            return path;
        }
    }
}