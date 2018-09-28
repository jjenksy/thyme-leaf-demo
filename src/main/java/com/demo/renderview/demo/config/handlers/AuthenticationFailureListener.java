package com.demo.renderview.demo.config.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Slf4j
public class AuthenticationFailureListener implements AuthenticationFailureHandler {


	@Override
	public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) throws IOException, ServletException {


		String errorMessage = "message.badCredentials";

//		response.sendRedirect(request.getContextPath() + "?error=true");
		if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
			errorMessage = "auth.message.disabled";

		} else if (exception.getMessage().equalsIgnoreCase("User account has expired")) {
			errorMessage = "auth.message.expired";
//			response.sendRedirect(request.getContextPath() + "?error=true");
		} else if (exception.getMessage().equalsIgnoreCase("blocked")) {
			errorMessage = "auth.message.blocked";
//			response.sendRedirect(request.getContextPath() + "?error=true");
		}
		log.error(errorMessage);
		response.sendRedirect(request.getRequestURI()+"?error=true&message=hello");
		request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
	}


}
