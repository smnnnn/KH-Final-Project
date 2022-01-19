package com.kh.project.review.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.project.review.model.vo.Review;
import com.kh.project.review.model.vo.ReviewUpload;

@Mapper
public interface ReviewMapper {

	List<Review> selectReviewList();

	int insertReview(Review review);

	int insertRvupload(ReviewUpload thumbnail);

	int increaseCount(int rvno);

	Review selectReview(int rvno);

	ReviewUpload selectThumbnail(int rvno);
	
	int deleteReview(int rvno);

	int deleteThumbnail(int rvno);

	int updateReview(Review review);

	int updateThumbnail(ReviewUpload thumbnail);

	int insertAddedThumbnail(@Param("rvno")int rvno, @Param("th")ReviewUpload thumbnail);


}
