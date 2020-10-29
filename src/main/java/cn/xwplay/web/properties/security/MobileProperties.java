package cn.xwplay.web.properties.security;

import cn.xwplay.web.enums.LoginResponseType;
import cn.xwplay.web.properties.SecurityConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MobileProperties {

    private LoginResponseType loginResponseType = LoginResponseType.JSON;
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
    private String registerUrl;

}
