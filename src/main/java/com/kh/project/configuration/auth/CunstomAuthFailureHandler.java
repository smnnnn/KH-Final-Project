package com.kh.project.configuration.auth;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Component
public class CunstomAuthFailureHandler implements AuthenticationFailureHandler { //extends SimpleUrlAuthenticationFailureHandler  // implements AuthenticationFailureHandler
	
	// private final String DEFAULT_FAILURE_URL = "/member/loginFail";
	
	//private final String DEFAULT_FAILURE_URL =  "/member/loginFail?error=true&exception="+errorMessage ;
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		log.info("로그인 실패 시 핸들러");
		String errorMessage = "";
		// String username = request.getParameter(loginidname);
		// String password = request.getParameter(loginpwdname);
		// errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
		log.info(exception.getClass().getTypeName());
		log.info(exception.getMessage());
		if (exception instanceof BadCredentialsException) {
			// request.setAttribute(errorMessage, "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요.");
			errorMessage = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
			log.info("setAttribute1" + errorMessage);
		} else if (exception instanceof UsernameNotFoundException) {
			// request.setAttribute(errorMessage, "계정이 존재하지 않습니다. 회원가입 진행 후 로그인 해주세요.");
			errorMessage = "계정이 존재하지 않습니다. 회원가입 진행 후 로그인 해주세요.";
			log.info("setAttribute3" + errorMessage);
		} else if (exception instanceof InternalAuthenticationServiceException) {
			// request.setAttribute(errorMessage, "내부적으로 발생한 시스템 문제로 인해 요청을 처리할 수 없습니다.
			// 관리자에게 문의하세요.");
			errorMessage = "내부적으로 발생한 시스템 문제로 인해 요청을 처리할 수 없습니다. 관리자에게 문의하세요.";
			log.info("setAttribute2" + errorMessage);
		} else if (exception instanceof AuthenticationCredentialsNotFoundException) {
			// request.setAttribute(errorMessage, "인증 요청이 거부되었습니다. 관리자에게 문의하세요.");
			errorMessage = "인증 요청이 거부되었습니다. 관리자에게 문의하세요.";
			log.info("setAttribute4" + errorMessage);
		} else {
			// request.setAttribute(errorMessage, "알 수 없는 이유로 로그인에 실패하였습니다 관리자에게 문의하세요.");
			errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다 관리자에게 문의하세요.";
			log.info("setAttribute5" + errorMessage);
		}
		errorMessage = URLEncoder.encode(errorMessage, "UTF-8");

		// request.setAttribute(loginidname, username);
		// request.setAttribute(loginpwdname, password);
		// setDefaultFailureUrl("/member/loginFail?error=true&exception="+errorMessage);
		request.getRequestDispatcher("/member/loginFail?error=true&exception=" + errorMessage).forward(request,
				response);
		log.info("포워딩");
		// setDefaultFailureUrl("/auth/login?error=true&exception="+errorMessage);
		// super.onAuthenticationFailure(request, response, exception);
	}

}
