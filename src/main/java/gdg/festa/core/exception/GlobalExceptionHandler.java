package gdg.festa.core.exception;

import gdg.festa.core.common.CommonResponseDto;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 예상하지 못한 예외를 처리 (서버, DB 예외 등)
    @ExceptionHandler(value = {Exception.class})
    public CommonResponseDto<?> handleException(Exception e) {
        log.error("handleException() in GlobalExceptionHandle throw Exception: {}", e.getMessage(), e);
        return CommonResponseDto.fail(new CustomException(ErrorCode.SERVER_ERROR));
    }

    // 개발자가 직접 정의한 예외를 처리하는 클래스
    // 예측 가능한 예외를 처리 (비즈니스 로직 실패 등)
    @ExceptionHandler(value = {CustomException.class})
    public CommonResponseDto<?> handleCommonException(CustomException e) {
        log.error("GlobalExceptionHandler catch CommonException : {}", e.getMessage());
        return CommonResponseDto.fail(e);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public CommonResponseDto<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("GlobalExceptionHandler catch MethodArgumentTypeMismatchException : {}", e.getMessage());
        return CommonResponseDto.fail(e);
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public CommonResponseDto<?> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException e) {
        log.error("GlobalExceptionHandler catch MissingServletRequestParameterException : {}", e.getMessage());
        return CommonResponseDto.fail(e);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public CommonResponseDto<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("GlobalExceptionHandler catch MethodArgumentNotValidException : {}", e.getMessage());
        return CommonResponseDto.fail(e);
    }

    @ExceptionHandler(value = {HandlerMethodValidationException.class})
    public CommonResponseDto<?> handleHandlerMethodValidationException(HandlerMethodValidationException e) {
        log.error("GlobalExceptionHandler catch HandlerMethodValidationException : {}", e.getMessage());
        return CommonResponseDto.fail(e);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public CommonResponseDto<?> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("GlobalExceptionHandler catch ConstraintViolationException : {}", e.getMessage());
        return CommonResponseDto.fail(e);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public CommonResponseDto<?> handleMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error(
                "handleArgumentNotValidException() in GlobalExceptionHandler throw HttpMessageNotReadableException : {}",
                e.getMessage());
        return CommonResponseDto.fail(e);
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    public CommonResponseDto<?> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error("GlobalExceptionHandler catch HttpMediaTypeNotSupportedException : {}", e.getMessage());
        return CommonResponseDto.fail(new CustomException(ErrorCode.UNSUPPORTED_MEDIA_TYPE));
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public CommonResponseDto<?> handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.error("GlobalExceptionHandler catch NoHandlerFoundException : {}", e.getMessage());
        return CommonResponseDto.fail(new CustomException(ErrorCode.NOT_FOUND_END_POINT));
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public CommonResponseDto<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("GlobalExceptionHandler catch HttpRequestMethodNotSupportedException : {}", e.getMessage());
        return CommonResponseDto.fail(new CustomException(ErrorCode.METHOD_NOT_ALLOWED));
    }

    @ExceptionHandler(value = {MissingServletRequestPartException.class})
    public CommonResponseDto<?> handleServletRequestParameterException(MissingServletRequestPartException e) {
        log.error(
                "handleArgumentNotValidException() in GlobalExceptionHandler throw MissingServletRequestPartException : {}",
                e.getMessage());
        return CommonResponseDto.fail(new CustomException(ErrorCode.MISSING_REQUEST_PART));
    }
}
