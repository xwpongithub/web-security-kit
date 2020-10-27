package cn.xwplay.web.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userDetailService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailServiceImpl implements UserDetailsService {

  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return User
            .withUsername(username)
            .disabled(false)
            .credentialsExpired(false)
            .accountLocked(false)
            .accountExpired(false)
            .password(passwordEncoder.encode("123456"))
            .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("admin,manager"))
            .build();
  }

  // AffirmativeBased  or的关系，只要一个过就过
  // ConsensusBased 比较投通过和不通过的voter的个数，哪种意见的voter个数多就按哪种来
  // UnanimousBased and的关系，只要一个不过就不过

  // 要判断一个请求过还是不过，首先需要一些系统配置信息，
  // ConfigAttribute 菜单信息
  // Authentication 用户信息
  // 当前请求信息
}
