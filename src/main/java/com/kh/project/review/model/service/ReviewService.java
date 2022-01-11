package com.kh.project.review.model.service;

import java.util.List;

import com.kh.project.review.model.vo.Review;
import com.kh.project.review.model.vo.ReviewUpload;

public interface ReviewService {

	List<Review> selectReviewList();

	int insertReview(Review review);

	int increaseCount(int rvno);

	Review selectReview(int rvno);

	ReviewUpload deleteThumbnail(int rvno);

	int deleteReview(int rvno);

}
