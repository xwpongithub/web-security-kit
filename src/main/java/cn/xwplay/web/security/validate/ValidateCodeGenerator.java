package cn.xwplay.web.security.validate;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码生成器
 * @author 肖文彭
 */
public interface ValidateCodeGenerator {

    /**
     * 生成验证码方法
     */
    ValidateCode generate(HttpServletRequest request);

}
