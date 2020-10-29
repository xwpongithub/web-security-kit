package cn.xwplay.web.properties.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QQProperties {

  private String appId;
  private String appSecret;
  private String providerId = "qq";

}
