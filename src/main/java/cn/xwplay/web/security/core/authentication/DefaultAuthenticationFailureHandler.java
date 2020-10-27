package cn.xwplay.web.security.core.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

//	private ObjectMapper objectMapper;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		log.info("登录失败");
		
//		if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
//			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//			response.setContentType("application/json;charset=UTF-8");
//			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
//		}else{
			super.onAuthenticationFailure(request, response, exception);
//		}
//
		
	}

}
