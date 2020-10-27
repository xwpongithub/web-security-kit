package cn.xwplay.web.security.validate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 校验码处理器，封装不同校验码的处理逻辑
 * @author 肖文彭
 */
public interface ValidateCodeProcessor {

  String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建校验码
     */
    void create(HttpServletRequest request, HttpServletResponse response);

    /**
     * 校验验证码
     */
    void validate(HttpServletRequest request);

}
