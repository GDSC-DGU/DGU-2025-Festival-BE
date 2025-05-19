package gdg.festa.core.util;

import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.util.StringUtils;

public class HeaderUtil {
    public static Optional<String> refineHeader(HttpServletRequest request, String headerName, String prefix) {
        String token = request.getHeader(headerName);

        if(!StringUtils.hasText(token) || !token.startsWith(prefix)) {
            throw new CustomException(ErrorCode.INVALID_REQUEST_HEAD);
        }

        return Optional.ofNullable(token.substring(prefix.length()));
    }
}
