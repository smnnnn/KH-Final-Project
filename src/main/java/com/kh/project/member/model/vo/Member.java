package com.kh.project.member.model.vo;

import java.util.Date;
import java.util.List;



import lombok.Data;

@Data
public class Member {

//	private int no;						// 회원번호
//	private String id;					// 회원아이디
//	private String pwd;					//회원비밀번호
//	private String tempPwdYn;			//임시비밀번호여부
//	private Date pwdChangedDatetime;	//회원비밀번호변경일자
//	private String pwdExpDate;			//회원비밀번호만료일자
//	private String name;				//회원이름
//	private Date registDatetime;		//회원가입일시
//	private int accumLoginCount;		//누적로그인횟수
//	private int loginFailedCount;		//로그인연속실패횟수
//	private String accLockYn;			//계정잠금여부
//	private String accInactiveYn;		//계정비활성화여부
//	private String accExpYn;			//계정만료여부
//	private Date accsecssionDatetime;	//계정탈퇴일시
//	private String accSecssionYn;
	
	
	private int no;						// 회원번호
	private String id;					// 회원아이디
	private String pwd;					//회원비밀번호
	private String tempPwdYn;			//임시비밀번호여부
	private Date pwdChangedDatetime;	//회원비밀번호변경일자
	private String name;				//회원이름
	private int phone;
	private String email;
	private String address;
	private Date registDatetime;		//회원가입일시
	private Date modifyDatetime;
	private int loginFailedCount;		//로그인연속실패횟수
	private Date accLockTime;
	private String accLockYn;			//계정잠금여부
	private Date accSecssionDatetime;	//계정탈퇴일시
	private String accSecssionYn;		//계정탈퇴여부
	
	/* 한 멤버는 여러 권한을 가질 수 있음 - TBL_MEMBER_ROLE과 조인한 결과 값 */
	private List<MemberRole> memberRoleList;		//보유권한목록

	public Member() {}
	
	public Member(int no, String id, String pwd, String tempPwdYn, Date pwdChangedDatetime, String name, int phone,
			String email, String address, Date registDatetime, Date modifyDatetime, int loginFailedCount,
			Date accLockTime, String accLockYn, Date accSecssionDatetime, String accSecssionYn,
			List<MemberRole> memberRoleList) {
		super();
		this.no = no;
		this.id = id;
		this.pwd = pwd;
		this.tempPwdYn = tempPwdYn;
		this.pwdChangedDatetime = pwdChangedDatetime;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.registDatetime = registDatetime;
		this.modifyDatetime = modifyDatetime;
		this.loginFailedCount = loginFailedCount;
		this.accLockTime = accLockTime;
		this.accLockYn = accLockYn;
		this.accSecssionDatetime = accSecssionDatetime;
		this.accSecssionYn = accSecssionYn;
		this.memberRoleList = memberRoleList;
	}

	
	
	
	 
	

}
