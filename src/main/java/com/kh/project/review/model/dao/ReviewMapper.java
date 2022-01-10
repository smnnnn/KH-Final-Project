package com.kh.project.review.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.project.review.model.vo.Review;

@Mapper
public interface ReviewMapper {

	List<Review> selectReviewList();

}
