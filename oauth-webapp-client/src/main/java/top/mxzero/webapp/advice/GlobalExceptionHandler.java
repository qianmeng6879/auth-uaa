package top.mxzero.webapp.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * @author Zero
 * @email qianmeng6879@163.com
 * @since 2023/3/5
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Object handlerException(Exception e) {
        LOGGER.error("error:{} {}", e.getClass().getName(), e.getMessage());
        return Map.of("error", e.getMessage(), "type", e.getClass().getName());
    }
}
