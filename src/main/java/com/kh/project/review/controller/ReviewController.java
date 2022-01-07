package com.kh.project.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.project.review.model.service.ReviewService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/review")
public class ReviewController {

	private ReviewService reviewService;
	
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@RequestMapping("list")
	public String selectReviewList() { //vo 만들어서 바꿔
	
		return "review/reviewList";
	}
	
	@RequestMapping("detail")
	public String selectReview() {
		
		return "review/reviewDetail";
	}
	
	
}
