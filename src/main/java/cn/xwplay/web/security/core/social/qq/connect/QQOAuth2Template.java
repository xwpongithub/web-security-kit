package cn.xwplay.web.security.core.social.qq.connect;

import cn.hutool.core.util.StrUtil;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

public class QQOAuth2Template extends OAuth2Template {

  public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
    super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
    setUseParametersForClientAuthentication(true);
  }

  @Override
  protected RestTemplate createRestTemplate() {
    RestTemplate restTemplate = super.createRestTemplate();
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
    return restTemplate;
  }

  @Override
  protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
    String responseStr = getRestTemplate().postForObject(accessTokenUrl,parameters,String.class);
    System.out.println("获取accessToken的响应：");
    System.out.println(responseStr);
    String[] items = StrUtil.split(responseStr,",");
    String accessToken = StrUtil.subAfter(items[0],"=",true);
    Long expireIn = Long.parseLong(StrUtil.subAfter(items[1],"=",true));
    String refreshToken = StrUtil.subAfter(items[2],"=",true);

    return new AccessGrant(accessToken,null,refreshToken,expireIn);
  }
}
