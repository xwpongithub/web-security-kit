package cn.xwplay.web.security.validate.sms;

import cn.xwplay.web.properties.SecurityConstants;
import cn.xwplay.web.security.validate.AbstractValidateCodeProcessor;
import cn.xwplay.web.security.validate.ValidateCode;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Resource
    private SmsCodeSender smsCodeSender;

    @Override
    public void send(HttpServletRequest request,HttpServletResponse response, ValidateCode validateCode)  {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = request.getParameter(paramName);
        smsCodeSender.send(mobile, validateCode.getCode());
    }

}
