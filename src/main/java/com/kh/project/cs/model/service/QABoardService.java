package com.kh.project.cs.model.service;

import java.util.List;
import java.util.Map;

import com.kh.project.cs.model.vo.QABoard;
import com.kh.project.cs.model.vo.Search;

public interface QABoardService {
	
	int getListCount(Search search);
	
//	List<QABoard> selectQAList(int page, Search search);
	
	Map<String, Object> selectQAList(int page, Search search);
}
