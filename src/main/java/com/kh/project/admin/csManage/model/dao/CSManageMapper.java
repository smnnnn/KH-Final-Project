package com.kh.project.admin.csManage.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.project.cs.model.vo.QABoard;

@Mapper
public interface CSManageMapper {

	List<QABoard> selectQuestionList();

	QABoard selectQuestionByNo(int no);

	int deleteAnswer(int ANo);
	
	int deleteQuestion(int QNo);

}
