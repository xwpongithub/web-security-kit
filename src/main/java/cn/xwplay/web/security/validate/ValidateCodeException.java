package cn.xwplay.web.security.validate;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常类
 * @author 肖文彭
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = 7091795199511138411L;

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
