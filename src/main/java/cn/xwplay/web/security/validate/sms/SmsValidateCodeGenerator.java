package cn.xwplay.web.security.validate.sms;

import cn.hutool.core.util.RandomUtil;
import cn.xwplay.web.security.validate.ValidateCode;
import cn.xwplay.web.security.validate.ValidateCodeGenerator;

import javax.servlet.http.HttpServletRequest;

public class SmsValidateCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ValidateCode generate(HttpServletRequest request) {
        String code = RandomUtil.randomNumbers(4);
        return new SmsCode(code,300);
    }

}
