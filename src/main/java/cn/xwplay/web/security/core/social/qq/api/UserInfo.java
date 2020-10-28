package cn.xwplay.web.security.core.social.qq.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserInfo implements Serializable {


  private static final long serialVersionUID = -2682554295776264742L;

  private Integer ret;
  private String msg;
  private String nickname;
  private String openId;
  private String is_lost;
  private String province;
  private String city;
  private String year;
  private String figureurl;
  private String figureurl_1;
  private String figureurl_2;
  private String figureurl_qq_1;
  private String figureurl_qq_2;
  private String gender;
  private String is_yellow_vip;
  private String vip;
  private String yellow_vip_level;
  private String level;
  private String is_yellow_year_vip;

}
