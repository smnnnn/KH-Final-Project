package com.kh.project.admin.csManage.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.project.admin.common.model.vo.Search;
import com.kh.project.admin.csManage.model.vo.Question;

@Mapper
public interface CSManageMapper {

	List<Question> selectQuestionList(Search search);	

	Question selectQuestionByNo(int no);

	int deleteAnswer(int ano);
	
	int deleteQuestion(int qno);

	int getListCount(int sort);

	int getAnswerStatusCount(int sort);

	int getSearchListCount(Search search);


}
