package cn.xwplay.web.security;

import cn.xwplay.web.properties.security.SecurityProperties;
import cn.xwplay.web.security.core.authentication.AbstractChannelSecurityConfig;
import cn.xwplay.web.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfiguration;
import cn.xwplay.web.properties.SecurityConstants;
import cn.xwplay.web.security.validate.ValidateCodeSecurityConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.annotation.Resource;

@EnableWebSecurity
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfiguration extends AbstractChannelSecurityConfig {

    @Resource
    private ValidateCodeSecurityConfiguration validateCodeSecurityConfiguration;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private SmsCodeAuthenticationSecurityConfiguration smsCodeAuthenticationSecurityConfiguration;
    @Resource
    private SpringSocialConfigurer springSocialConfigurer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfiguration)
                .and()
                .apply(smsCodeAuthenticationSecurityConfiguration)
                .and()
                .apply(springSocialConfigurer)
                .and()
                .userDetailsService(userDetailsService)
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        "/login.html","/favicon.ico","/auth/*")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

    }

}
