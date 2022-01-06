package com.kh.project.cs.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.project.cs.model.vo.Answer;
import com.kh.project.cs.model.vo.PageInfo;
import com.kh.project.cs.model.vo.QABoard;
import com.kh.project.cs.model.vo.Search;

@Mapper
public interface QABoardMapper {
	
	int getListCount(Search search);
	
//	int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
//	int endRow = startRow + pi.getBoardLimit() - 1;
//	List<QABoard> selectQAList(PageInfo pi, Search search); //맵에다 담아서 넘겨야 할까? startrow,endrow,searchCondition,searchValue
	
	List<QABoard> selectQAList(int startRow, int endRow, String searchCondition, String searchValue); 
	
	QABoard selectQA(int qNo);
	
	int insertQA(QABoard qaBoard); //유저넘버랑 카테고리 넘버
	
	int increaseCount(int qNo);
	
	int updateQA(QABoard qaBoard);
	
	int deleteQA(int qNo);

	Answer selectAnswer(int qNo);

	int insertReply(Answer answer);

	int updateAnswer(Answer answer);

	int deleteAnswer(int qno);
}
