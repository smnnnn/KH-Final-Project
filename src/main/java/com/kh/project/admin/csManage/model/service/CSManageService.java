package com.kh.project.admin.csManage.model.service;

import java.util.List;

import com.kh.project.admin.common.model.vo.Search;
import com.kh.project.admin.csManage.model.vo.Question;

public interface CSManageService {

	List<Question> selectQuestionList(int startRow, int endRow, int sort, Search search);

	Question selectQuestionByNo(int no);

	int deleteQuestion(int qno, int ano);

	int getListCount(int sort, Search search);

	int getAnswerStatusCount(int sort);

}
