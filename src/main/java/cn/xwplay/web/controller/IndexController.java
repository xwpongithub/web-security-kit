package cn.xwplay.web.controller;

import cn.hutool.core.util.StrUtil;
import cn.xwplay.web.common.Response;
import cn.xwplay.web.pojo.entity.SocialUserInfo;
import cn.xwplay.web.properties.SecurityConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IndexController {

    private final RequestCache requestCache = new HttpSessionRequestCache();
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final ProviderSignInUtils providerSignInUtils;

    @GetMapping
    public String index() {
        return "index";
    }

    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Response requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是:" + targetUrl);
            if (StrUtil.endWithIgnoreCase(targetUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, "/login.html");
            }
        }
        return Response.error(401,"访问的服务需要身份认证，请引导用户到登录页");
    }

    @PostMapping("register")
    public void register(HttpServletRequest req) {
      String userId = "从数据库或者后端获取";
      providerSignInUtils.doPostSignUp(userId,new ServletWebRequest(req));
    }

    @GetMapping("social/user")
    public SocialUserInfo socialUserInfo(HttpServletRequest req) {
      SocialUserInfo userInfo = new SocialUserInfo();
      Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(req));
      userInfo.setProviderId(connection.getKey().getProviderId());
      userInfo.setProviderUserId(connection.getKey().getProviderUserId());
      userInfo.setNickname(connection.getDisplayName());
      userInfo.setAvatar(connection.getImageUrl());
      return userInfo;
    }
}
