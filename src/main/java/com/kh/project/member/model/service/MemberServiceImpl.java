package com.kh.project.member.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.project.member.model.dao.MemberMapper;
import com.kh.project.member.model.vo.Authority;
import com.kh.project.member.model.vo.DogInformation;
import com.kh.project.member.model.vo.Member;
import com.kh.project.member.model.vo.MemberRole;
import com.kh.project.member.model.vo.UserImpl;
import com.kh.project.member.model.vo.WithdrawalReason;
import com.kh.project.reservation.model.vo.ReservationInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("MemberService")
public class MemberServiceImpl implements MemberService{
	
	
		private MemberMapper memberMapper;
		
		@Autowired 
		public MemberServiceImpl(MemberMapper memberMapper) {
			this.memberMapper = memberMapper;
		}
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/* 우리가 만든 타입으로 유저 조회 */
		Member member = memberMapper.findMemberById(username);  
		// DB에 없는 username 들어오면 member null됨
		
		/* null 값을 없게 하기 위해 조회된 값이 없을 시 빈 객체 */
		if(member == null) member = new Member();  //=> member.getMemberRoleList() = null
		
		/* GrantedAuthority 타입의 컬렉션 객체에 조회해온 권한을 담는다 */
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if(member.getMemberRoleList() != null) {
			List<MemberRole> roleList = member.getMemberRoleList(); //직접적으로 담을수 없었던 롤 리스트를
			
			for(MemberRole role : roleList) {
				Authority authority = role.getAuthority();
				
				if( authority != null ) {
					authorities.add(new SimpleGrantedAuthority(authority.getName()));  //authority.getName() => ROLE_ADMIN  어드민은 반복문 2번 돌음
				}
			} 
			
		}

	log.info(authorities.toString());
		
		// return member; 이렇게 안 됨
		/* 스프링 시큐리티 모듈에서 사용되는 타입인 User객체로 id, pwd, 접근 권한을 담아 객체 만들어 리턴*/  //또는 User 상속 받아서 커스터마이징도 하나의 방법
		//return new User(member.getId(), member.getPwd(), authorities);  //스프링시큐리티에서 제공하고 있는 유저타입 객체 리턴 (유저네임,비번,허가된 권한)

		//또는 User 상속 받아서 커스터마이징도 하나의 방법
		/* 멤버의 id, pwd 외의 다른 정보를 담기 위해 User를 상속한 UserImpl 클래스를 만들고 해당 타입으로 처리 */
		UserImpl user = new UserImpl(member.getId(), member.getPwd(), authorities);
		
		// 락 처리 이거 이용 
		// new User(username, username, false, false, false, false, authorities);
		user.setDetails(member); //모든 필드값 옮겨올 수 있음.
		
		return user;
	}

	
	@Transactional
	@Override
	public void signUp(Member member, DogInformation dogInformation) {
		
		/* BCryptPasswordEncoder 사용한 rawPassword -> encodePassword */
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setPwd(passwordEncoder.encode(member.getPwd()));  //encode 메소드에 인코딩하고 싶은 라우패스워드 넣음  ... 인코딩한걸 멤버객체에 다시 넣음. 인코딩 된 해당값 인서트 할 수 있게
	
		
		/* TBL_MEMBER TABLE INSERT */  
		memberMapper.insertMember(member); // 멤버 테이블에 대한 인서트 수행에 대한 것
		
		/* TBL_MEMBER_ROLE_INSERT */    //유저가 가진 어솔리티도 인서트 되어야 함
		MemberRole memberRole = new MemberRole(); // 인서트  MemberRole 형식
		memberRole.setAuthorityCode(1);  // 일반 회원에 대해서 .. 규정 상황에 따라 달라짐 (일반/총관리/서브관리 발생되는 기능 ?)
		// 
		memberMapper.insertMemberRole(memberRole);
		
		// 빈객체 안에 도그 객체 없음 그냥 클라이언트가 입력한 거 입력 인풋태그랑 핸들러 입력한 값 가져오기 
		memberMapper.insertDogInformaion(dogInformation);
		 
		
		
	}



	@Override
	public int idCheck(String userId) {
		return memberMapper.idCheck(userId);
	}



	@Override
	public String idFind(String name, String email) {
		return memberMapper.idFind(name, email);
	}



	@Transactional
	@Override
	public void withdrawal(Member member, WithdrawalReason withdrawal) {
			
			memberMapper.withdrawal(withdrawal);
			memberMapper.updateaccSecssionYn(member);	
		
	}


	@Transactional
	@Override
	public Member myPageUpdate(Member member, DogInformation dogInformation) {
		/* BCryptPasswordEncoder 사용한 rawPassword -> encodePassword */
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setPwd(passwordEncoder.encode(member.getPwd()));  //encode 메소드에 인코딩하고 싶은 라우패스워드 넣음  ... 인코딩한걸 멤버객체에 다시 넣음. 인코딩 된 해당값 인서트 할 수 있게
	
		
		
		/* TBL_MEMBER TABLE INSERT */  
		memberMapper.myPageUpdate(member); // 멤버 테이블에 대한 인서트 수행에 대한 것
		
		// 등록된 도그 값이 있으면 update 없으면 인서트 if문 써서
		// int dogNo = memberMapper.selectDogInformation(member.getId());
		int dogNo = memberMapper.selectDogInformation(dogInformation.getUserNo());
		log.info("service dogNo : " + dogNo);
		// 빈객체 안에 도그 객체 없음 그냥 클라이언트가 입력한 거 입력 인풋태그랑 핸들러 입력한 값 가져오기 
		if(dogNo > 0) {
		memberMapper.updateDogInformaion(dogInformation);
		log.info("update");
		}else {
		memberMapper.updateInsertDogInformaion(dogInformation);
		log.info("insert");

		}
		
		return memberMapper.findMemberById(member.getId());
		
	}



	@Override
	public void pwdUpdate(Member member) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setPwd(passwordEncoder.encode(member.getPwd()));
		
		memberMapper.pwdUpdate(member);
	}



	@Override
	public int pwdFind(String id, String email) {
		
		int pwdFindNo = memberMapper.pwdFind(id, email);
		
		log.info("service dogNo : " + pwdFindNo);
		
		return memberMapper.pwdFind(id, email);
		
	}



	@Override
	public List<ReservationInfo> reservationList(String id) {
		return memberMapper.reservationList(id);
	}



	@Override
	public int reservationCancel(int reservation_no) {
		return memberMapper.reservationCancel(reservation_no);
	}



	


	

}
