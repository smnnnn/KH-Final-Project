package com.kh.project.admin.csManage.model.service;

import java.util.List;

import com.kh.project.admin.csManage.model.vo.Question;

public interface CSManageService {

	List<Question> selectQuestionList(int startRow, int endRow, int sort);

	Question selectQuestionByNo(int no);

	int deleteQuestion(int qno, int ano);

	int getListCount(int sort);

	int getAnswerStatusCount(int sort);

}
