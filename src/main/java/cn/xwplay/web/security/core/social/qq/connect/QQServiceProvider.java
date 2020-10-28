package cn.xwplay.web.security.core.social.qq.connect;

import cn.xwplay.web.security.core.social.qq.api.QQ;
import cn.xwplay.web.security.core.social.qq.api.impl.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

  private final String appId;

  private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
  private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

  @Override
  public QQ getApi(String accessToken) {
    return new QQImpl(accessToken,appId);
  }

  public QQServiceProvider(String appId,String appSecret) {
    super(new OAuth2Template(
        appId, appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN
    ));
    this.appId = appId;
  }

}
