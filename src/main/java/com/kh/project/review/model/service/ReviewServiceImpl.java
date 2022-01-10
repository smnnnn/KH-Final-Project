package com.kh.project.review.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.project.review.model.dao.ReviewMapper;
import com.kh.project.review.model.vo.Review;
import com.kh.project.review.model.vo.ReviewUpload;

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

	@Override
	public int insertReview(Review review) {
		
		/* 리뷰 테이블 삽입*/
		int reviewResult = reviewMapper.insertReview(review);
		
		/* 리뷰 업로드 테이블 삽입 */
		ReviewUpload thumbnail = review.getThumbnail();
		int uploadResult = reviewMapper.insertRvupload(thumbnail);
		
		int result = 0;
		if(reviewResult > 0 && uploadResult > 0) {
			result = 1;
		} else {
			result = 0;
		}
		return result;
	}


	
	
}
