package cn.xwplay.web.properties.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SocialProperties {

  private String filterProcessUrl = "/auth";
  private QQProperties qq = new QQProperties();

}
