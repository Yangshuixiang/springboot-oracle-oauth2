package example.demo.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义验证码错误异常
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
