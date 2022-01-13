package com.kh.project.review.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		log.info("리뷰 목록 {} ",reviewList);
		
		model.addAttribute(reviewList);
		
		return "review/reviewList";
	}
	
/*	@RequestMapping("detail")
	public String selectReview() { 
		
		return "review/reviewDetail";
	}*/
	
	@GetMapping("")
	public String selectReview(@RequestParam("rvno") int rvno, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		/* cookie 활용한 조회수 무한 증가 방지 처리 */
		Cookie[] cookies = request.getCookies();
		
		String rvhit = "";
		
		if(cookies != null && cookies.length > 0) {
			for(Cookie c : cookies) {
				/* 읽은 게시물 rvno를 저장해두는 쿠키의 이름 rvhit이 있는지 확인*/
				if(c.getName().equals("rvhit")) {
					rvhit = c.getValue();
				}
			}
		}
		
		// 처음 읽는 게시글일 경우
		// Ex. "|1||22||100|" 와 같은 rvhit cookie의 value 값에서 indexOf로 해당 문자열 찾기
		if(rvhit.indexOf("|" + rvno + "|") == -1) {
			// 기본 rvhit 값에 지금 요청한 rvno 값 추가하여 새로운 쿠키 생성
			Cookie newRvhit = new Cookie("rvhit", rvhit + "|" + rvno + "|");
			// 초 단위로 유효 기간 설정 가능
			// newRvhit.setMaxAge(1 * 24 * 60 * 60);
			
			// 설정하지 않을 시 session cookie
			// 클라이언트가 저장하고 있을 수 있도록 응답에 담는다
			response.addCookie(newRvhit);
			
			// DB의 해당 게시글 조회수 증가
			int result = reviewService.increaseCount(rvno);
			
			if(result > 0) {
				log.info("조회수 증가 성공");
			} else{
				log.info("조회수 증가 실패");
			}		
		}
		
		Review review = reviewService.selectReview(rvno);
		
		log.info("리뷰 조회 : {}", review);
		
		model.addAttribute("review",review);
		
		return "review/reviewDetail";
	}
	
	
	
	@GetMapping("insert")
	public String insertReview() {
		
		return "review/addReviewPage";
	}
	
	@PostMapping("insert") 
	public String addReview(@RequestParam MultipartFile thumbnail, 
							HttpServletRequest request) throws IllegalStateException, IOException {

		String rvtitle = request.getParameter("rvtitle");
		String rvcontent = request.getParameter("rvcontent");
		int tno = Integer.parseInt(request.getParameter("tno"));
		int resNo = Integer.parseInt(request.getParameter("resNo")); //예약 기능 완성되면 진짜 예약번호로 바꾸기
		int userNo = Integer.parseInt(request.getParameter("userNo")); //로그인 기능 완성되면 진짜 회원번호로 바꾸기
		
		Review review = new Review();
		review.setRvtitle(rvtitle);
		review.setRvcontent(rvcontent);
		review.setTno(tno);
		review.setResNo(resNo);
		review.setUserNo(userNo);

		log.info("before insert review {}", review);
		
		if(thumbnail != null){
			log.info("thumbnail {}", thumbnail);
			
			String root = request.getSession().getServletContext().getRealPath("/");
			String uploadPath = root + "uploadFiles\\review";
			
			log.info("root {}", root);
			
			/* 해당 경로 없을 경우 make directory */
			File mkdir = new File(uploadPath);
			if(!mkdir.exists()) mkdir.mkdirs();
			
			 if(!thumbnail.isEmpty()){
				/* 파일명 확인 */
				String originFileName = thumbnail.getOriginalFilename();
				log.info("originFileName {}", originFileName);
				String ext = originFileName.substring(originFileName.lastIndexOf("."));
				
				/* 파일명 변경 처리 */
				String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
				log.info("savedName : " + savedName);
				
				/* 파일을 저장함*/
				thumbnail.transferTo(new File(uploadPath + "\\" + savedName));
				
				ReviewUpload reviewUpload = new ReviewUpload();
				reviewUpload.setChangedName(savedName);
				reviewUpload.setOriginName(originFileName);
				reviewUpload.setFilePath("/uploadFiles/review/");
				
				review.setThumbnail(reviewUpload);	
			 }
			
		}

		
		int result = reviewService.insertReview(review);
		
		if(result > 0) {
			log.info("리뷰 등록 성공");
		} else{
			log.info("리뷰 등록 실패");
			
		}		
		
		
		return "redirect:/review/list";
	}
		
	
	@RequestMapping("delete")
	public String deleteReview(int rvno,HttpServletRequest request) {
		
		/* 리뷰, 리뷰업로드 테이블의 rvno 일치하는 행 status Y -> N으로 변경 
		 * 서버에 저장된 이미지 파일의 정보 알아와서 삭제 처리 */
		ReviewUpload deletedUpload = reviewService.deleteThumbnail(rvno);
		
		if(deletedUpload != null) {
			/*DB에서 Y -> N update 수행 완료 되었으므로 서버의 파일 삭제*/
			String root = request.getSession().getServletContext().getRealPath("/");
			File deletedPhoto = new File(root + deletedUpload.getFilePath() + deletedUpload.getChangedName());
			deletedPhoto.delete();
		} else {
			/*에러 메세지*/
		}
		
		return "redirect:/review/list";
	}
	

	@RequestMapping("updateView")
	public String updateReviewView(int rvno, Model model) {
		
		/*입력한 정보 수정 화면에 가져오기*/
		Review review = reviewService.selectReview(rvno);
		log.info("수정할 정보 {}",review);
		model.addAttribute("review", review);
		return "review/updateReviewView";
	}

	
	@PostMapping("/update")
	public String updateReview(@RequestParam MultipartFile thumbnail, 
			HttpServletRequest request) throws IllegalStateException, IOException {
		/* 원래 저장된 파일명 */
		String changedName = request.getParameter("changedName");
		/* 리뷰 수정정보 */
		int rvno = Integer.parseInt(request.getParameter("rvno"));
		int tno = Integer.parseInt(request.getParameter("tno"));
		String rvtitle = request.getParameter("rvtitle");
		String rvcontent = request.getParameter("rvcontent");
		
		Review review = new Review();
		review.setRvno(rvno);
		review.setTno(tno);
		review.setRvtitle(rvtitle);
		review.setRvcontent(rvcontent);
		
		/* 밑에서 계속 쓰이는 것들 */
		ReviewUpload reviewUpload = new ReviewUpload();				
		String root = request.getSession().getServletContext().getRealPath("/");
		String uploadPath = root + "uploadFiles\\review";
		
		/* 첨부 파일 있을시 파일 정보 */
		if(thumbnail != null) {
			
			if(!thumbnail.isEmpty()){
				/* 파일명 확인 */
				String originFileName = thumbnail.getOriginalFilename();
				log.info("originFileName {}", originFileName);
				String ext = originFileName.substring(originFileName.lastIndexOf("."));
				
				/* 파일명 변경 처리 */
				String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
				log.info("savedName : " + savedName);
				
				/* 파일을 저장함*/
				thumbnail.transferTo(new File(uploadPath + "\\" + savedName));		
				
				/* 테이블 값 수정 */

				reviewUpload.setChangedName(savedName);
				reviewUpload.setOriginName(originFileName);
				reviewUpload.setFilePath("/uploadFiles/review/");
				
				// 원래 저장된 파일이 있었다면 -> DB에서 update 처리 & 서버에서 기존 파일 delete 처리
				if(changedName != null) {
					reviewUpload.setDeletedName(changedName);
				}
				
				
				review.setThumbnail(reviewUpload);	
			 }
			
		}
		
		int result = reviewService.updateReview(review);
		
		if(result > 0) {
			log.info("수정 성공");
			/* 수정 성공시 덮어쓰기 된 사진 삭제 */
			if(reviewUpload.getDeletedName() != null) {
				File deletedFile = new File(uploadPath + reviewUpload.getDeletedName());
				deletedFile.delete();
			}
			
		} else {
			log.info("수정 실패");
			/* 수정 실패 시 수정을 위해 첨부된 사진 삭제 */
			if(reviewUpload != null) {
				File failedFile = new File(uploadPath + reviewUpload.getChangedName());
				failedFile.delete();
			}
			
		}
		
		return "redirect:/review?rvno=" + rvno;
	}
	
	
	
	
}
