package cn.xwplay.web.security.core.social;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultSpringSocialConfigurer extends SpringSocialConfigurer {

  private final String filterProcessUrl;

  @Override
  protected <T> T postProcess(T object) {
    SocialAuthenticationFilter filter = (SocialAuthenticationFilter)object;
    filter.setFilterProcessesUrl(filterProcessUrl);
    filter.setSignupUrl("/register.html");
    return (T)filter;
  }
}
