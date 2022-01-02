package com.kh.project.cs.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.project.cs.model.dao.QABoardMapper;
import com.kh.project.cs.model.vo.PageInfo;
import com.kh.project.cs.model.vo.QABoard;
import com.kh.project.cs.model.vo.Search;

@Service("qaBoardService") /*qABoardService 이렇게 해야되나?*/
public class QABoardServiceImpl implements QABoardService {

	private final QABoardMapper boardMapper;
	
	@Autowired
	public QABoardServiceImpl(QABoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
	@Override
	public int getListCount(Search search) {
		// TODO Auto-generated method stub
		return 0;
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
		
		// 페이징 처리된 게시글 목록 조회
		List<QABoard> boardList = boardMapper.selectQAList(startRow, endRow, search);
		
		// 페이징 처리된 게시글 목록 조회
//		List<QABoard> boardList = boardMapper.selectQAList(pi, search);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("pi", pi);
		returnMap.put("boardList", boardList);
		
		//페이지 인포
//		return boardMapper.selectQAList(pi, search); 
		return returnMap;
	}




}
