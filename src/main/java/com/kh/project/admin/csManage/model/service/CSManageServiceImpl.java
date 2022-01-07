package com.kh.project.admin.csManage.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.project.admin.csManage.model.dao.CSManageMapper;
import com.kh.project.cs.model.vo.QABoard;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CSManageServiceImpl implements CSManageService {

	private CSManageMapper csManageMapper;
	
	@Autowired
	public CSManageServiceImpl(CSManageMapper csManageMapper) {
		this.csManageMapper = csManageMapper;
	}
	
	@Override
	public List<QABoard> selectQuestionList() {
		
		/* 문의 목록 조회 */
		List<QABoard> questionList = csManageMapper.selectQuestionList();
		
		return questionList;
	}

	@Override
	public QABoard selectQuestionByNo(int no) {
		
		QABoard question = csManageMapper.selectQuestionByNo(no);
		
		return question;
	}

	@Transactional
	@Override
	public int deleteQuestion(int QNo, int ANo) {
				
		int answerResult = 1;
		if(ANo != 0) {
			answerResult = csManageMapper.deleteAnswer(ANo);
		}
		
		int result = csManageMapper.deleteQuestion(QNo);
		
		return result > 0 && answerResult > 0 ? 1 : 0;
	}

}
