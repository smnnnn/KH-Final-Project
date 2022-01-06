package com.kh.project.admin.csManage.model.service;

import java.util.List;

import com.kh.project.cs.model.vo.QABoard;

public interface CSManageService {

	List<QABoard> selectQuestionList();

	QABoard selectQuestionByNo(int no);

	int deleteQuestion(int QNo, int ANo);

}
