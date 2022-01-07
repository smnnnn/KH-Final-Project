package com.kh.project.admin.csManage.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.project.admin.csManage.model.vo.Question;

@Mapper
public interface CSManageMapper {

	List<Question> selectQuestionList(int startRow, int endRow, int sort);

	Question selectQuestionByNo(int no);

	int deleteAnswer(int ano);
	
	int deleteQuestion(int qno);

	int getListCount(int sort);

	int getAnswerStatusCount(int sort);

}
