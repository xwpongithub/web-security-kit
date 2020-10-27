package cn.xwplay.web.security.validate;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 获取当前有效的验证处理器
 * @author 肖文彭
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ValidateCodeProcessorHolder {

    private final Map<String,ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null) {
                throw new ValidateCodeException("验证码处理器【" + name + "】不存在");
        }
        return processor;
    }

}
