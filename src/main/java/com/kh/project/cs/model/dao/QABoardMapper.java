package com.kh.project.cs.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.project.cs.model.vo.PageInfo;
import com.kh.project.cs.model.vo.QABoard;
import com.kh.project.cs.model.vo.Search;

@Mapper
public interface QABoardMapper {
	
	int getListCount(Search search);
	
	List<QABoard> selectQAList(PageInfo pi, Search search);
	
	QABoard selectQA(int Qno);
	
	int insertQA(QABoard qaBoard);
	
	int increaseCount(int rvNo);
	
	int updateQA(QABoard qaBoard);
	
	int deleteQA(int Qno);
}
