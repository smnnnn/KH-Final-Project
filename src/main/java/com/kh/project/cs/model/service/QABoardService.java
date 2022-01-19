package com.kh.project.cs.model.service;

import java.util.List;
import java.util.Map;

import com.kh.project.cs.model.vo.Answer;
import com.kh.project.cs.model.vo.QABoard;
import com.kh.project.cs.model.vo.Search;

public interface QABoardService {
	
	int getListCount(Search search);
	
	Map<String, Object> selectQAList(int page, Search search);

	int increaseCount(int qNo);

	QABoard selectQA(int qNo);
	
	int insertQA(QABoard qaBoard);

	int updateQA(QABoard qaBoard);

	int deleteQA(int qNo);

	Answer insertReply(Answer answer);

	Answer selectReply(int qno);

	int updateReply(Answer answer);

	int deleteReply(int qno);

	int selectAdminById(String string);
	
}
