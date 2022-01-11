package com.kh.project.member.model.vo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRole {
	
	private int memberNo;
	private int authorityCode;
	
	/* 권한 코드별로 가지는 권한을 나타냄 - TBL_AUTORITY */
	private Authority authority;    //조인하려고? (리스트 아니라 대응하는 한 개 클래스 만들어줌)


	
	
	

}
