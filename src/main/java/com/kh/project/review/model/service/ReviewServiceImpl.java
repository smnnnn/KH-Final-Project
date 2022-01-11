package com.kh.project.review.model.service;

import java.io.File;
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
		int uploadResult = 0;
		/* 리뷰 업로드 테이블 삽입 */
		ReviewUpload thumbnail = review.getThumbnail();
		
		if (thumbnail != null) {
			
			uploadResult = reviewMapper.insertRvupload(thumbnail);
			
			// 실패 시 저장 된 사진 삭제
			if(uploadResult == 0) {
				File failedFile = new File(thumbnail.getFilePath() + thumbnail.getChangedName());
				failedFile.delete();
			}
		} else {
			uploadResult = 1;
		}
		
		int result = 0;
		if(reviewResult > 0 && uploadResult > 0) {
			result = 1;
		} else {
			result = 0;
		}
		return result;
	}

	@Override
	public int increaseCount(int rvno) {
		
		return reviewMapper.increaseCount(rvno);
	}

	@Override
	public Review selectReview(int rvno) {

	/*	Review review = reviewMapper.selectReview(rvno);
		
		ReviewUpload reviewUpload;
		
		reviewUpload = reviewMapper.selectThumbnail(rvno);

		review.setThumbnail(reviewUpload);*/
		
		// thumbnail 없다고 null뜨는데
		
		return reviewMapper.selectReview(rvno);
	}

	@Override
	public ReviewUpload deleteThumbnail(int rvno) {
		
		ReviewUpload reviewUpload = reviewMapper.selectThumbnail(rvno);
		
		return null;
	}

	@Override
	public int deleteReview(int rvno) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	
}
