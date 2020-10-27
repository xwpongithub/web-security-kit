package cn.xwplay.web.security.validate.image;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.xwplay.web.security.validate.ValidateCodeGenerator;

import javax.servlet.http.HttpServletRequest;

/**
 * 图片验证码生成器
 * @author 肖文彭
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ImageCode generate(HttpServletRequest request) {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(100,45,4,20);
        captcha.createCode();
        return new ImageCode(captcha.getImage(),captcha.getCode(),300);
    }

}
