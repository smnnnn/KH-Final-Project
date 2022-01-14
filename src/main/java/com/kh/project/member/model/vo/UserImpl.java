package com.kh.project.member.model.vo;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;



public class UserImpl extends User {
	
	
	private int no;						// 회원번호
	private String id;					// 회원아이디
	private String pwd;					//회원비밀번호
	private String tempPwdYn;			//임시비밀번호여부
	private Date pwdChangedDatetime;	//회원비밀번호변경일자
	private String name;				//회원이름
	private String phone;
	private String email;
	private String address;
	private Date registDatetime;		//회원가입일시
	private Date modifyDatetime;
	private int loginFailedCount;		//로그인연속실패횟수
	private Date accLockTime;
	private String accLockYn;			//계정잠금여부
	private Date accSecssionDatetime;	//계정탈퇴일시
	private String accSecssionYn;		//계정탈퇴여부
	
	
	private String petName;
	private int petAge;
	private String breed;
	private String status;
	private String petGender;
	
	
	public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}
	
	/* 한 멤버는 여러 권한을 가질 수 있음 - TBL_MEMBER_ROLE과 조인한 결과 값 */
	private List<MemberRole> memberRoleList;		//보유권한목록
	
	// 다뤄야할 친구들 
		public void setDetails(Member member) {
			this.no = member.getNo();
			this.id = member.getId();
			this.pwd = member.getPwd();
			this.tempPwdYn = member.getTempPwdYn();
			this.pwdChangedDatetime = member.getPwdChangedDatetime();
			this.name = member.getName();
			this.phone = member.getPhone();
			this.email = member.getEmail();
			this.address = member.getAddress();
			this.registDatetime = member.getRegistDatetime();
			this.modifyDatetime = member.getModifyDatetime();
			this.loginFailedCount = member.getLoginFailedCount();
			this.accLockTime = member.getAccLockTime();
			this.accLockYn = member.getAccLockYn();
			this.accSecssionDatetime = member.getAccSecssionDatetime();
			this.accSecssionYn = member.getAccSecssionYn();
			this.petName = member.getPetName();
			this.petAge = member.getPetAge();
			this.breed = member.getBreed();
			this.status = member.getStatus();
			this.petGender = member.getPetGender();
			
		}

//		public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities, int no,
//				String id, String pwd, String tempPwdYn, Date pwdChangedDatetime, String name, String phone, String email,
//				String address, Date registDatetime, Date modifyDatetime, int loginFailedCount, Date accLockTime,
//				String accLockYn, Date accSecssionDatetime, String accSecssionYn, List<MemberRole> memberRoleList) {
//			super(username, password, authorities);
//			this.no = no;
//			this.id = id;
//			this.pwd = pwd;
//			this.tempPwdYn = tempPwdYn;
//			this.pwdChangedDatetime = pwdChangedDatetime;
//			this.name = name;
//			this.phone = phone;
//			this.email = email;
//			this.address = address;
//			this.registDatetime = registDatetime;
//			this.modifyDatetime = modifyDatetime;
//			this.loginFailedCount = loginFailedCount;
//			this.accLockTime = accLockTime;
//			this.accLockYn = accLockYn;
//			this.accSecssionDatetime = accSecssionDatetime;
//			this.accSecssionYn = accSecssionYn;
//			this.memberRoleList = memberRoleList;
//		}

		public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities, int no,
				String id, String pwd, String tempPwdYn, Date pwdChangedDatetime, String name, String phone,
				String email, String address, Date registDatetime, Date modifyDatetime, int loginFailedCount,
				Date accLockTime, String accLockYn, Date accSecssionDatetime, String accSecssionYn, String petName,
				int petAge, String breed, String status, String petGender, List<MemberRole> memberRoleList) {
			super(username, password, authorities);
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
			this.petName = petName;
			this.petAge = petAge;
			this.breed = breed;
			this.status = status;
			this.petGender = petGender;
			this.memberRoleList = memberRoleList;
		}
		
		
		
		
	

}
