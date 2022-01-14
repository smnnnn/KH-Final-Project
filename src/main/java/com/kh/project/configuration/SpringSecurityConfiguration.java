package com.kh.project.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.kh.project.member.model.service.MemberService;


@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private MemberService memberService;
	
	@Autowired  
	public SpringSecurityConfiguration(MemberService memberService) {
		this.memberService = memberService;
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
				/* 요청 보안 수준의 세부적인 설정 */
				/* "/menu/**" 요청은 인증이 되어야 함을 명시 */
//				.antMatchers("/menu/**").authenticated()  //로그인 한 사람만 ()요청 수행가능
//				/* 더 상세하게 "/menu/**"의 get 요청은 ROLE_MEMBER 권한을 가진 사람에게만 허용 */
//				/* hasRole 안의 값 앞에는 자동으로 ROLE_가 붙음 */
//				.antMatchers(HttpMethod.GET, "/menu/**").hasRole("MEMBER")
//				/* "/menu/**"의 POST 요청은 ROLE_ADMIN 권한을 가진 사람에게만 허용 */
//				.antMatchers(HttpMethod.POST, "/menu/**").hasRole("ADMIN")
//				/* "/admin/**"의 요청은 ROLE_ADMIN 권환을 가진 사람에게만 허용 */
//				.antMatchers("/admin/**").hasRole("ADMIN")
				/* 그 외의 요청은 모두 허가함 - 게스트 사용자도 접근 가능 */
				.anyRequest().permitAll()  
			.and()	   //요청에 따라 리턴값이 달라지니까 ? 위쪽 설정과 구분 지어서 작성
				/* 로그인 설정 */
				.formLogin()
				/* 로그인 페이지 설정 */
				.loginPage("/member/login")
				/* 로그인 성공 시 랜딩 페이지 설정 */
				.successForwardUrl("/")
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
				/* exception 발생했을 때 설정? */
				.exceptionHandling()
				/* 인가 되지 않았을 때 - 권한이 없을 때 이동할 페이지 */
				.accessDeniedPage("/common/denied");
		
		
	}
	
	@Override    //AuthenticationManagerBuilder 로그인 됐나 관리 할때  passwordEncoder() 인코더 사용할 것.
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		/* 로그인, 로그아웃은 MemberController에 작성하지 않고 스프링 시큐리티 모듈을 통해 처리 */
		/* 유저 인증을 위해 사용할 MemberService 등록, 사용하는 패스워드 인코딩 방식 설정 */
		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
	}
	
	
	
	
	
	
	
	
	

}