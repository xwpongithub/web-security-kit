package cn.xwplay.web.security.core.social.qq.connect;

import cn.xwplay.web.security.core.social.qq.api.QQ;
import cn.xwplay.web.security.core.social.qq.api.UserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class QQAdapter implements ApiAdapter<QQ> {

  @Override
  public boolean test(QQ qq) {
    return true;
  }

  @Override
  public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {
    UserInfo userInfo = qq.getUserInfo();
    connectionValues.setDisplayName(userInfo.getNickname());
    connectionValues.setImageUrl(userInfo.getFigureurl_qq_1());
    connectionValues.setProfileUrl(null);
    connectionValues.setProviderUserId(userInfo.getOpenId());
  }

  @Override
  public UserProfile fetchUserProfile(QQ qq) {
    return null;
  }

  @Override
  public void updateStatus(QQ qq, String s) {

  }

}
