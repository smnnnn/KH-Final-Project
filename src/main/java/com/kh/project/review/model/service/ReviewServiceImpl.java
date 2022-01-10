package com.kh.project.review.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.project.review.model.dao.ReviewMapper;
import com.kh.project.review.model.vo.Review;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

	private final ReviewMapper reviewMapper;

	@Autowired
	public ReviewServiceImpl(ReviewMapper reviewMapper) {
		this.reviewMapper = reviewMapper;
	}
	
	@Override
	public List<Review> selectReviewList() {
		return reviewMapper.selectReviewList();
	}


	
	
}
