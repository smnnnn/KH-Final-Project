package com.kh.project.cs.model.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.kh.project.configuration.KhProjectApplication;
import com.kh.project.cs.model.vo.QABoard;
import com.kh.project.cs.model.vo.Search;


// JUnit 이용한 단위 테스트

@SpringBootTest
/* 스프링이 제공하는 SpringRunner를 이용하여 테스트 실행*/
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {KhProjectApplication.class})
public class QABoardServiceTests {

	@Autowired
	private QABoardService qaBoardService;
	
	@Test
	public void selectQAList() {

//		List<QABoard> qaList = qaBoardService.selectQAList(1, null);
		
		Map<String, Object> qaList = qaBoardService.selectQAList(1, null);
		
		assertNotNull(qaList);
		
//		for(QABoard qa : qaList) {
//			System.out.println(qa);
//		}
	}
	
	/* assertNoNull(a) : a가 null이 아닌지 확인 
	 * assertEquals(a, b) : 객체 a와 b가 일치함을 확인
	 * assertArrayEquals(a, b) : 배열 a와 b가 일치함을 확인
	 * assertSame(a, b) : 객체 a와 b가 동일 객체임을 확인
	 * assertTrue(a) : 조건 a가 참인지 확인*/
}
