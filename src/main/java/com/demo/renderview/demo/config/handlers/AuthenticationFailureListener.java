package com.demo.renderview.demo.config.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFailureListener implements AuthenticationFailureHandler {

	private static Logger logger = LoggerFactory.getLogger(AuthenticationFailureListener.class);
	private static final String BAD_CREDENTIALS_MESSAGE = "bad_credentials_message";
	private static final String CREDENTIALS_EXPIRED_MESSAGE = "credentials_expired_message";
	private static final String DISABLED_MESSAGE = "disabled_message";
	private static final String LOCKED_MESSAGE = "locked_message";

	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException ex) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String userName = req.getParameter("j_username");
		logger.error("[AuthenticationFailure]:" + " [Username]:" + userName + " [Error message]:" + ex.getMessage());

		if (ex instanceof BadCredentialsException) {
			res.sendRedirect("/login?message=" + BAD_CREDENTIALS_MESSAGE);
		} else if (ex instanceof CredentialsExpiredException) {
			res.sendRedirect("/login?message=" + CREDENTIALS_EXPIRED_MESSAGE);
		} else if (ex instanceof DisabledException) {
			res.sendRedirect("/login?message=" + DISABLED_MESSAGE);
		} else if (ex instanceof LockedException) {
			res.sendRedirect("/login?message=" + LOCKED_MESSAGE);
		}
	}
}
