package cn.xwplay.web.properties.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.security")
@Getter
@Setter
@ToString
public class SecurityProperties {

  private PcProperties pc = new PcProperties();
  private MobileProperties mobile = new MobileProperties();
  private ValidateCodeProperties code = new ValidateCodeProperties();

}
