package com.kh.project.admin.memberManage.model.vo;

import java.util.Date;
import java.util.List;

import com.kh.project.admin.reservationManage.model.vo.Dog;
import com.kh.project.admin.reservationManage.model.vo.ReservationManage;
import com.kh.project.member.model.vo.MemberRole;

import lombok.Data;

@Data
public class MemberInfo {
	private int no;						// 회원번호
	private String id;					// 회원아이디
	private String pwd;                 //회원비밀번호
	private String name;				//회원이름
	private String phone;
	private String email;
	private String address;
	private String dogName;             //반려견이름
	private Date enrollDatetime;		//회원가입일시
	private Date accSecssionDatetime;	//계정탈퇴일시
	private String accSecssionYn;		//계정탈퇴여부
	private String accSecessionReason;  //탈퇴사유
	private String otherOpinions;       //개선사항
	private List<MemberRole> memberRoleList;		//보유권한목록
	
	private Dog dog;        // 반려견정보
	private List<ReservationManage> reservation;  // 예약정보

}
