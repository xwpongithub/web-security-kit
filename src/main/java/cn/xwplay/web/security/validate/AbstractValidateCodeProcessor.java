package cn.xwplay.web.security.validate;

import cn.hutool.core.util.StrUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 验证码校验处理器基类
 * @author 肖文彭
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    @Resource
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    /**
     * 发送校验码，由子类实现
     */
    protected abstract void send(HttpServletRequest request,HttpServletResponse response, C validateCode);

    @Override
    public void create(HttpServletRequest request, HttpServletResponse response) {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request,response, validateCode);
    }

    @Override
    public void validate(HttpServletRequest request) {
        ValidateCodeType processorType = getValidateCodeType();
        String sessionKey = getSessionKey(request);
        HttpSession httpSession = request.getSession();
        C codeInSession = (C)httpSession.getAttribute(sessionKey);
        String codeInRequest;
        codeInRequest = request.getParameter(processorType.getParamNameOnValidate());
        if (StrUtil.isBlank(codeInRequest)) {
            throw new ValidateCodeException(processorType + "验证码的值不能为空");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException(processorType + "验证码不存在");
        }
        if (codeInSession.isExpired()) {
            httpSession.removeAttribute(sessionKey);
            throw new ValidateCodeException(processorType + "验证码已过期");
        }
        if (!StrUtil.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(processorType + "验证码不匹配");
        }
        httpSession.removeAttribute( sessionKey);
    }

    /**
     * 生成校验码
     */
    @SuppressWarnings("unchecked")
    private C generate(HttpServletRequest request) {
        String type = getValidateCodeType().toString().toLowerCase();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器【" + generatorName + "】不存在");
        }
        return (C) validateCodeGenerator.generate(request);
    }

    /**
     * 保存校验码
     */
    private void save(HttpServletRequest request, C validateCode) {
        request.getSession().setAttribute(getSessionKey(request), validateCode);
    }

    /**
     * 根据请求的url获取校验码的类型
     */
    private ValidateCodeType getValidateCodeType() {
        String type = StrUtil.subBefore(getClass().getSimpleName(), "CodeProcessor",false);
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    /**
     * 构建验证码放入session时的key
     */
    private String getSessionKey(HttpServletRequest request) {
        return SESSION_KEY_PREFIX + getValidateCodeType().toString().toUpperCase();
    }

}
