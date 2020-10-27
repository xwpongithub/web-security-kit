package cn.xwplay.web.security.validate.sms;

import cn.xwplay.web.security.validate.ValidateCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class SmsCode extends ValidateCode  {

    public SmsCode(String code, int expireIn){
        super(code, expireIn);
    }

    public SmsCode( String code, Date expireTime){
        super(code, expireTime);
    }

}
