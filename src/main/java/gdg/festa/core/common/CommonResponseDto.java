package gdg.festa.core.common;

import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

public record CommonResponseDto<T>(@JsonIgnore HttpStatus httpStatus,
                                   @NotNull Boolean success,
                                   @Nullable T data,
                                   @Nullable ExceptionDto error) {

    public static <T> CommonResponseDto<T> ok(@Nullable final T data) { //성공
        return new CommonResponseDto<T>(HttpStatus.OK, true, data, null);
    }

    public static <T> CommonResponseDto<T> created(@Nullable final T data) {
        return new CommonResponseDto<>(HttpStatus.CREATED, true, data, null);
    }

    // delete 성공 시 반환
    public static <T> CommonResponseDto<T> noContent() {
        return new CommonResponseDto<>(HttpStatus.NO_CONTENT, true, null, null);
    }

    // CommonException 개발자가 정의한 예외를 처리하는 클래스
    public static CommonResponseDto<Object> fail(final CustomException e) {
        return new CommonResponseDto<>(
                e.getErrorCode().getHttpStatus(), false,
                null, ExceptionDto.of(e.getErrorCode())
        );
    }

    // @RequestParam, @ModelAttribute
    public static CommonResponseDto<Object> fail(final HandlerMethodValidationException e) {
        return new CommonResponseDto<>(
                HttpStatus.BAD_REQUEST, false,
                null, ExceptionDto.of(ErrorCode.INVALID_REQUEST_PARAMETER)
        );
    }

    // @Valid @RequestBody
    public static CommonResponseDto<Object> fail(final MethodArgumentNotValidException e) {
        return new CommonResponseDto<>(
                HttpStatus.BAD_REQUEST, false,
                null, new MethodArgumentNotValidExceptionDto(e)
        );
    }

    // 컨트롤러가 아닌 다른 계층에서 @Validated 선언하고 파라미터나 필드에 @Valid 선언
    public static CommonResponseDto<Object> fail(final ConstraintViolationException e) {
        return new CommonResponseDto<>(
                HttpStatus.BAD_REQUEST, false,
                null, new MethodArgumentNotValidExceptionDto(e)
        );
    }

    public static CommonResponseDto<Object> fail(final MethodArgumentTypeMismatchException e) {
        return new CommonResponseDto<>(
                HttpStatus.BAD_REQUEST, false,
                null, ExceptionDto.of(ErrorCode.ARGUMENT_TYPE_MISMATCH)
        );
    }

    public static CommonResponseDto<Object> fail(final MissingServletRequestParameterException e) {
        return new CommonResponseDto<>(
                HttpStatus.BAD_REQUEST, false,
                null, ExceptionDto.of(ErrorCode.MISSING_REQUEST_PARAMETER)
        );
    }

    public static CommonResponseDto<Object> fail(final HttpMessageNotReadableException e) {
        return new CommonResponseDto<>(
                HttpStatus.BAD_REQUEST, false,
                null, ExceptionDto.of(ErrorCode.INVALID_REQUEST_BODY)
        );
    }
}
