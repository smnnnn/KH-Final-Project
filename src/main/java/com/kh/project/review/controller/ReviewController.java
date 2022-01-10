package com.kh.project.review.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.project.review.model.service.ReviewService;
import com.kh.project.review.model.vo.Review;
import com.kh.project.review.model.vo.ReviewUpload;

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
	public String selectReviewList(Model model) { 
	
		List<Review> reviewList = reviewService.selectReviewList();
		// model.addAttribute 하면 되자너
		
		log.info("리뷰 목록 {} ",reviewList);
		
		model.addAttribute(reviewList);
		
		return "review/reviewList";
	}
	
	@RequestMapping("detail")
	public String selectReview() {
		
		return "review/reviewDetail";
	}
	
	@GetMapping("insert")
	public String insertReview() {
		
		return "review/addReviewPage";
	}
	
	@PostMapping("insert") 
	public String addReview(@RequestParam MultipartFile thumbnail, 
							HttpServletRequest request) throws IllegalStateException, IOException {
		
//		String singleFileDescription = request.getParameter("singleFileDescription");
		String rvtitle = request.getParameter("rvtitle");
		String rvcontent = request.getParameter("rvcontent");
		int tno = Integer.parseInt(request.getParameter("tno"));
		int resNo = Integer.parseInt(request.getParameter("resNo"));
		int userNo = Integer.parseInt(request.getParameter("userNo")); //로그인 기능 완성되면 진짜 회원번호로 바꾸기
		
		Review review = new Review();
		review.setRvcontent(rvcontent);
		review.setRvcontent(rvcontent);
		review.setTno(tno);
		review.setResNo(resNo);
		review.setUserNo(userNo);
		
		log.info("before insert review {}", review);
		log.info("thumbnail {}", thumbnail);
		
		String root = request.getSession().getServletContext().getRealPath("/");
		String uploadPath = root + "uploadFiles\\review";
		
		log.info("root {}", root);
		
//		String filePath = root + "\\uploadFiles";
		
		File mkdir = new File(uploadPath);
		if(!mkdir.exists()) mkdir.mkdirs();
		
		/* 파일명 확인 */
		String originFileName = thumbnail.getOriginalFilename();
		log.info("originFileName {}", originFileName);
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		
		/* 파일명 변경 처리 */
		String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
		log.info("savedName : " + savedName);
		
		thumbnail.transferTo(new File(uploadPath + "\\" + savedName));
		
		ReviewUpload reviewUpload = new ReviewUpload();
		reviewUpload.setChangedName(savedName);
		reviewUpload.setOriginName(originFileName);
		reviewUpload.setFilePath("/uploadFiles/review/");
		
		List<ReviewUpload> photoList = new ArrayList<>(); //사진이 한장이면 이럴 필요가 없지
		
		
		
		
		
		return "redirect:/review/list";
	}
		
	
	
	
	// 테스트(나중에 지울것)
	@GetMapping("upload")
	public String uploadTest() {
		return "review/uploadTest";
	}
	
	@PostMapping("upload")
	public String uploadingTest(@RequestParam MultipartFile singleFile) {
		
		log.info("singleFile {}", singleFile);
		return "";
	}
	
/*	@PostMapping("reviewUpload")
	public ResponseEntity<?> summerimage(@RequestParam("file") MultipartFile img, HttpServletRequest request) throws IOException {
		String path = request.getServletContext().getRealPath("resources/theme/summerimages");
		Random random = new Random();
	
		long currentTime = System.currentTimeMillis();
		int	randomValue = random.nextInt(100);
		String fileName = Long.toString(currentTime) + "_"+randomValue+"_a_"+img.getOriginalFilename();
		
		File file = new File(path , fileName);
		img.transferTo(file);
		return ResponseEntity.ok().body("/images/reviewUpload/"+fileName);

	}
*/	
	
}
