package cn.xwplay.web.security.core.social.qq.api.impl;

import cn.hutool.core.util.StrUtil;
import cn.xwplay.web.security.core.social.qq.api.QQ;
import cn.xwplay.web.security.core.social.qq.api.UserInfo;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

  private static final String URL_OPENID ="https://graph.qq.com/oauth2.0/me?access_token={}";
  private static final String URL_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key={}&openid={}";

  private final String appId;
  private final String openId;

  public QQImpl(String accessToken,String appId) {
    super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    this.appId = appId;
    String getOpenIdUrl = StrUtil.format(URL_OPENID,accessToken);
    String result = getRestTemplate().getForObject(getOpenIdUrl,String.class);
    System.out.println("请求openid返回值：");
    System.out.println(result);
    this.openId = StrUtil.subBetween(result,"\"openid\":\"","\"}");
  }

  @Override
  public UserInfo getUserInfo() {
    String getUserInfoUrl = StrUtil.format(URL_USER_INFO,appId,openId);
    UserInfo userInfo =  getRestTemplate().getForObject(getUserInfoUrl,UserInfo.class);
    userInfo.setOpenId(openId);
    return userInfo;
  }

}
