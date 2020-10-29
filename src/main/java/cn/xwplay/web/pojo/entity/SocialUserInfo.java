package cn.xwplay.web.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SocialUserInfo {

  private String providerId;
  private String providerUserId;
  private String nickname;
  private String avatar;

}
