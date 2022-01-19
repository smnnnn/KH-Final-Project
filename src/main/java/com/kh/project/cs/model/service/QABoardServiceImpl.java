package com.kh.project.cs.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.kh.project.cs.model.dao.QABoardMapper;
import com.kh.project.cs.model.vo.Answer;
import com.kh.project.cs.model.vo.PageInfo;
import com.kh.project.cs.model.vo.QABoard;
import com.kh.project.cs.model.vo.Search;



@Transactional
@Service("qaBoardService") 
public class QABoardServiceImpl implements QABoardService {

	private final QABoardMapper boardMapper;
	
	@Autowired
	public QABoardServiceImpl(QABoardMapper boardMapper) {
		
		this.boardMapper = boardMapper;
	}
	
	@Override
	public int getListCount(Search search) {

		return boardMapper.getListCount(search);
	}


	@Override
	public Map<String, Object> selectQAList(int page, Search search) {
//		public List<QABoard> selectQAList(int page, Search search) {

		// 게시글 총 개수 구하기
		int listCount = boardMapper.getListCount(search);
		
		// PageInfo 객체 만들기
		PageInfo pi = new PageInfo(page, listCount, 10, 10);

		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		String searchCondition = search.getSearchCondition();
		String searchValue = search.getSearchValue();
		
		// 페이징 처리된 게시글 목록 조회
		List<QABoard> boardList = boardMapper.selectQAList(startRow, endRow, searchCondition, searchValue);

		// 페이징 처리된 게시글 목록 조회
//		List<QABoard> boardList = boardMapper.selectQAList(pi, search);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("pi", pi);
		returnMap.put("boardList", boardList);
		
		//페이지 인포
//		return boardMapper.selectQAList(pi, search); 
		return returnMap;
	}

	@Override
	public int increaseCount(int qNo) {

		return boardMapper.increaseCount(qNo);
	}

	@Override
	public QABoard selectQA(int qNo) {
		
		QABoard board = boardMapper.selectQA(qNo);
		/* 댓글 조회 추가 */
		board.setAnswer(boardMapper.selectAnswer(qNo));
		
		return board;
	}

	@Override
	public int insertQA(QABoard qaBoard) {
		
		return boardMapper.insertQA(qaBoard);
	}

	@Override
	public int updateQA(QABoard qaBoard) {
		
		return boardMapper.updateQA(qaBoard);
	}

	@Override
	public int deleteQA(int qNo) {
		
		return boardMapper.deleteQA(qNo);
	}

	@Override
	public Answer insertReply(Answer answer) {

		Answer newAnswer = null;
		
		int result = boardMapper.insertReply(answer);
		
		if(result > 0) {
			newAnswer = boardMapper.selectAnswer(answer.getQno());
			//
		}
		
		return newAnswer;
	}

	@Override
	public Answer selectReply(int qno) {
		
		return boardMapper.selectAnswer(qno);
	}

	@Override
	public int updateReply(Answer answer) {
		
		return boardMapper.updateAnswer(answer);
	}

	@Override
	public int deleteReply(int qno) {
		
		return boardMapper.deleteAnswer(qno);
	}

	@Override
	public int selectAdminById(String string) {
		// TODO Auto-generated method stub
		return boardMapper.selectAdminById(string);
	}




}
