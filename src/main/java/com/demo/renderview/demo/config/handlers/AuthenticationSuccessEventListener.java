package com.demo.renderview.demo.config.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent>  {

	@Autowired
	private HttpServletRequest request;


	@Override
	public void onApplicationEvent(final AuthenticationSuccessEvent e) {
		// final WebAuthenticationDetails auth = (WebAuthenticationDetails) e.getAuthentication().getDetails();
		// if (auth != null) {
		// loginAttemptService.loginSucceeded(auth.getRemoteAddress());
		// }
		final String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			log.error("Successful login {}",request.getRemoteAddr());
		} else {
			log.error(xfHeader.split(",")[0]);
		}
	}
}
