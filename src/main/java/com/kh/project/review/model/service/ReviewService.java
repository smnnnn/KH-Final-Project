package com.kh.project.review.model.service;

import java.util.List;

import com.kh.project.review.model.vo.Review;

public interface ReviewService {

	List<Review> selectReviewList();

	int insertReview(Review review);

}
