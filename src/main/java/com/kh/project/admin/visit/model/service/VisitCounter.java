package com.kh.project.admin.visit.model.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kh.project.admin.adminManage.model.dao.AdminMapper;
import com.kh.project.admin.visit.model.vo.Visit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VisitCounter implements HttpSessionListener {
	private AdminMapper adminMapper;
	
	@Autowired
	public VisitCounter(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}
	
	public static int count;
	
	public static int getCount() {
		return count;
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {		
		// 세션이 만들어질 때 호출
		HttpSession session = event.getSession();  // request에서 얻는 session과 동일한 객체
		session.setMaxInactiveInterval(60*20);
		
		count++;
		
//		session.getServletContext().log(session.getId() + " 세션생성 " + ", 접속자수 : " + count);
		
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		Visit vc = new Visit();
		vc.setVisitIp(req.getRemoteAddr());
		vc.setVisitAgent(req.getHeader("User-Agent"));  
		int result = adminMapper.insertVisitor(vc);
//		log.info("session input result : {}", result);
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// 세션이 소멸될 때 호출
		count--;
		if(count < 0)
			count = 0;
		
		HttpSession session = event.getSession();
//		session.getServletContext().log(session.getId() + " 세션소멸 " + ", 접속자수 : " + count);
	}
	

}
