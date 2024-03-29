package by.itacademy.web.util;

import by.itacademy.core.dto.error.ErrorDto;
import by.itacademy.core.dto.error.MultipleErrorDto;
import by.itacademy.core.dto.error.SingleErrorDto;
import by.itacademy.core.enums.ErrorType;
import by.itacademy.core.exception.AuthorizationException;
import by.itacademy.core.exception.EntityNotFoundException;
import by.itacademy.core.exception.FileDownloadException;
import by.itacademy.core.exception.InvalidVersionException;
import by.itacademy.core.exception.VerificationException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionGlobalHandler {

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
                        fieldError.getDefaultMessage())
                )
                .collect(Collectors.toList());
        MultipleErrorDto multipleError = new MultipleErrorDto(
                ErrorType.STRUCTURED_ERROR.toString(), errors);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(multipleError);
    }

    @ExceptionHandler
    public ResponseEntity<List<SingleErrorDto>> handleDataIntegrityViolationException(
            DataIntegrityViolationException e) {

        Throwable rootCause = NestedExceptionUtils.getMostSpecificCause(e);
        String message = rootCause.getMessage();
        SingleErrorDto singleError = new SingleErrorDto(
                ErrorType.ERROR.toString(), message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(List.of(singleError));
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            UnexpectedTypeException.class,
            InvalidDataAccessApiUsageException.class,
            ConstraintViolationException.class,
            MissingPathVariableException.class})
    public ResponseEntity<List<SingleErrorDto>> handleDefaultUserError() {

        SingleErrorDto singleError = new SingleErrorDto(
                ErrorType.ERROR.toString(), DEFAULT_USER_ERROR_MESSAGE);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(List.of(singleError));
    }

    @ExceptionHandler({InvalidVersionException.class,
            EntityNotFoundException.class,
            VerificationException.class,
            AuthorizationException.class,
            FileDownloadException.class})
    public ResponseEntity<List<SingleErrorDto>> handleInvalidDataException(
            RuntimeException e) {

        SingleErrorDto error = new SingleErrorDto(
                ErrorType.ERROR.toString(), e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(List.of(error));
    }

    @ExceptionHandler()
    public ResponseEntity<List<SingleErrorDto>> handleServerException(Throwable e) {

        SingleErrorDto error = new SingleErrorDto(
                ErrorType.ERROR.toString(), DEFAULT_SERVER_ERROR_MESSAGE);
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(error));
    }

    @ExceptionHandler()
    public ResponseEntity<List<SingleErrorDto>> handleServletException(ServletException e) {

        SingleErrorDto error = new SingleErrorDto(
                ErrorType.ERROR.toString(), e.getMessage());
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(List.of(error));
    }
}