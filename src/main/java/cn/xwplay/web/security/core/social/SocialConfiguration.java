package cn.xwplay.web.security.core.social;

import cn.xwplay.web.properties.security.QQProperties;
import cn.xwplay.web.properties.security.SecurityProperties;
import cn.xwplay.web.security.core.social.qq.connect.QQConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@EnableSocial
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SocialConfiguration extends SocialConfigurerAdapter {

  private final DataSource dataSource;
  private final SecurityProperties securityProperties;
  @Autowired(required = false)
  private ConnectionSignUp connectionSignUp;

  @Override
  public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
    JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText());
    if (connectionSignUp!=null) {
      jdbcUsersConnectionRepository.setConnectionSignUp(connectionSignUp);
    }
    return jdbcUsersConnectionRepository;
  }

  @Override
  public UserIdSource getUserIdSource() {
    return new AuthenticationNameUserIdSource();
  }

  @Bean
  public SpringSocialConfigurer socialSecurityConfig() {
    return new DefaultSpringSocialConfigurer(securityProperties.getSocial().getFilterProcessUrl());
  }

  @Override
  public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
                                     Environment environment) {
    configurer.addConnectionFactory(createQQConnectionFactory());
  }

  public ConnectionFactory<?> createQQConnectionFactory() {
    QQProperties qq = securityProperties.getSocial().getQq();
    return new QQConnectionFactory(qq.getProviderId(), qq.getAppId(), qq.getAppSecret());
  }

  @Bean
  public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
    return new ProviderSignInUtils(connectionFactoryLocator,getUsersConnectionRepository(connectionFactoryLocator));
  }
}
