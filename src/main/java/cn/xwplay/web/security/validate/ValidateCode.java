package cn.xwplay.web.security.validate;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 验证码实体类
 * @author 肖文彭
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class ValidateCode implements Serializable {

    private static final long serialVersionUID = -2861961958486840869L;
    /**
     * 验证码
     */
    protected String code;
    /**
     * 验证码过期时间
     */
    protected Date expireTime;

    /**
     *
     * @param code 验证码
     * @param expireInSeconds 在几秒后过期
     */
    protected ValidateCode(String code,int expireInSeconds) {
       this.code = code;
       this.expireTime = DateUtil.date().offset(DateField.SECOND,expireInSeconds);
    }

    /**
     * 当前验证码是否过期
     */
    public boolean isExpired() {
        return DateUtil.date().isAfter(expireTime);
    }

}
