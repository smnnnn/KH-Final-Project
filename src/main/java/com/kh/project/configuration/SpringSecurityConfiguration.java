package com.kh.project.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.kh.project.configuration.auth.CunstomAuthFailureHandler;
import com.kh.project.member.model.service.MemberService;


@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private MemberService memberService;
	/* 로그인 실패 핸들러 의존성 주입 */
	private AuthenticationFailureHandler customFailurHandler;

	
	@Autowired  
	public SpringSecurityConfiguration(MemberService memberService) {
		this.memberService = memberService;
	}
	
	/* AuthenticationManager Bean 등록 - 회원정보 수정 세션에 적용하기 위한 빈등록 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();     
	}


	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**"); 
	}
	
	
	/* HTTP 요청에 대한 설정 */
	@Override 
	protected void configure(HttpSecurity http) throws Exception { 
		http
			
			/* .csrf().disable() */
			/* 요청에 대한 권한 체크 */
			.authorizeRequests()
				/* ROLE_SUB_ADMIN 권한을 가진 서브관리자 */
				.antMatchers("/admin").hasRole("SUB_ADMIN")   
				.antMatchers("/admin/dashboard").hasRole("SUB_ADMIN")    
				.antMatchers("/admin/visitCount").hasRole("SUB_ADMIN")  
				.antMatchers("/admin/mypage").hasRole("SUB_ADMIN")
				.antMatchers("/admin/sub_veterinarianModify").hasRole("SUB_ADMIN")
				.antMatchers("/admin/sub_veterinarianRegist").hasRole("SUB_ADMIN")
				/* ROLE_ADMIN 권환을 가진 총관리자만 허용 */
				.antMatchers("/admin/account/**").hasRole("ADMIN")
				.antMatchers("/admin/cs/**").hasRole("ADMIN")
				.antMatchers("/admin/hospital/**").hasRole("ADMIN")
				.antMatchers("/admin/member/**").hasRole("ADMIN")
				.antMatchers("/admin/reservation/**").hasRole("ADMIN")
				/* 문의게시판 */
				.antMatchers("/qaBoard").hasRole("MEMBER") 
				.antMatchers("/qaBoard/insert").authenticated()  
				.antMatchers("/qaBoard/updateView").authenticated() 
				.antMatchers("/qaBoard/delete").authenticated()
				/* 리뷰게시판 */
				.antMatchers("/review/insert").authenticated()
				.antMatchers("/review/delete").authenticated()
				.antMatchers("/review/updateView").authenticated()
				/* 진료 예약 */
				.antMatchers("/reservation/reservation_typeChoice").authenticated()
				.antMatchers("/reservation/reservation_timeChoice").authenticated()
				.antMatchers("/reservation/timeChoice").authenticated()
				.antMatchers("/reservation/reservation_form/**").authenticated()
				/* 마이 페이지 */
				.antMatchers("/member/myPage").authenticated()  
				.antMatchers("/member/withdrawal").authenticated() 
				/* 그 외의 요청은 모두 허가함 - 게스트 사용자도 접근 가능 */
				.anyRequest().permitAll()  
			.and()	   //요청에 따라 리턴값이 달라지니까 ? 위쪽 설정과 구분 지어서 작성
				/* 로그인 설정 */
				.formLogin()
				/* 로그인 페이지 설정 */
				.loginPage("/member/login")
				/* 로그인 성공 시 랜딩 페이지 설정 */
				.successForwardUrl("/")
				/* 로그인 실패 시 핸들러 */
				.failureHandler(failureHandler())
				.usernameParameter("username")
				.passwordParameter("password")
			.and()
				/* 로그아웃 설정 */
				.logout()
				/* 로그아웃 요청 주소 */
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				/* JSESSIONID 쿠키 삭제 */
				.deleteCookies("JSESSIONID")
				/* 세션 만료 */
				.invalidateHttpSession(true)
				/* 로그아웃 성공 시 랜딩 페이지 */
				.logoutSuccessUrl("/")
			.and()
				/* exception 발생했을 때 설정 */
				.exceptionHandling()
				/* 인가 되지 않았을 때 - 권한이 없을 때 이동할 페이지 */
				.accessDeniedPage("/common/denied");
		
		
	}
	
	@Override   
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		/* 로그인, 로그아웃은 MemberController에 작성하지 않고 스프링 시큐리티 모듈을 통해 처리 */
		/* 유저 인증을 위해 사용할 MemberService 등록, 사용하는 패스워드 인코딩 방식 설정 */
		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
	}
	
	
	 @Bean
	    public AuthenticationFailureHandler failureHandler() {
	        return new CunstomAuthFailureHandler();
	    }
	
	
	
	
	

}