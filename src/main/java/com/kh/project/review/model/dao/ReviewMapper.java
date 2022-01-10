package com.kh.project.review.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.project.review.model.vo.Review;
import com.kh.project.review.model.vo.ReviewUpload;

@Mapper
public interface ReviewMapper {

	List<Review> selectReviewList();

	int insertReview(Review review);

	int insertRvupload(ReviewUpload thumbnail);

}
