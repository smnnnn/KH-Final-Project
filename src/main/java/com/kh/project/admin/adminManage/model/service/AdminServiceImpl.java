package com.kh.project.admin.adminManage.model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.project.admin.adminManage.model.dao.AdminMapper;
import com.kh.project.admin.adminManage.model.vo.DashBoard;
import com.kh.project.admin.memberManage.model.vo.MemberInfo;
import com.kh.project.admin.visit.model.vo.VisitCount;
import com.kh.project.member.model.vo.MemberRole;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService{
	
	private AdminMapper adminMapper;
	
	@Autowired
	public AdminServiceImpl(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}

	@Override
	public int getListCount() {
		return adminMapper.getListCount();
	}
	
	@Override
	public List<MemberInfo> selectAdminList(int startRow, int endRow) {		
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);

		List<MemberInfo> adminList = adminMapper.selectAdminList(map);
		for(MemberInfo admin : adminList) {
			List<MemberRole> roleList =  adminMapper.selectAdminRole(admin.getNo());
			admin.setMemberRoleList(roleList);
		}
		return adminList;
	}

	@Override
	public MemberInfo selectAdminByNo(int no) {
		return adminMapper.selectAdminByNo(no);
	}

	@Override
	public int modifyAdminAuthority(int no, int authority) {
		int result = 0;
		if(authority == 2) {
			/* authority == 2 : authorityCode 3 삭제 */
			authority = 3;
			result = adminMapper.deleteAdminAuthorityOne(no, authority);	 
		} else {
			/* authority == 3 : authorityCode 3 추가 */
			authority = 3;
			result = adminMapper.insertAdminAuthority(no, authority);			
		}
		
		return result;
	}

	@Transactional
	@Override
	public int deleteAdmin(int adminNo) {
		int result = adminMapper.deleteAdmin(adminNo);
		int deleteAuthority = adminMapper.deleteAdminAuthority(adminNo);
		
		return result > 0 && deleteAuthority > 0 ? 1 : 0;
	}

	@Transactional
	@Override
	public int registAdmin(MemberInfo admin) {
		/* BCryptPasswordEncoder 사용한 rawPassword -> encodePassword */
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		admin.setPwd(passwordEncoder.encode(admin.getPwd()));  
		
		// 병원주소를 기본주소로 생성
		admin.setAddress("06234,서울특별시 강남구 테헤란로 10길 9,4층");		
		int result = adminMapper.registAdmin(admin);
		
		MemberRole memberRole = new MemberRole(); 
		memberRole.setAuthorityCode(1); 
		MemberRole memberRole2 = new MemberRole(); 
		memberRole2.setAuthorityCode(2); 
		
		int baseRole = adminMapper.registBaseRole(memberRole);
		int role = adminMapper.registAdminRole(memberRole2);
				
		return result > 0 && baseRole > 0 && role > 0 ? 1 : 0;
	}

	@Override
	public int modifyAdminInfo(MemberInfo changeInfo) {
		/* BCryptPasswordEncoder 사용한 rawPassword -> encodePassword */
		if(changeInfo.getPwd() != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			changeInfo.setPwd(passwordEncoder.encode(changeInfo.getPwd()));  			
		}
		
		return adminMapper.modifyAdminInfo(changeInfo);
	}

	@Override
	public DashBoard dashBoard() {
		/* 회원 수 조회 */
		int num = 1;
		int newMemberCnt = adminMapper.getMemberCount(num);
		num = 2;
		int seceMemberCnt = adminMapper.getMemberCount(num);
		num = 3;
		int totalMemberCnt = adminMapper.getMemberCount(num);
		
		/* 예약 수 조회 */
		int resNum = 1;
		int internalCnt = adminMapper.getReservationCount(resNum);
		resNum = 2;
		int surgeryCnt = adminMapper.getReservationCount(resNum);
		resNum = 3;
		int ophthalCnt = adminMapper.getReservationCount(resNum);
		resNum = 4;
		int orthoCnt = adminMapper.getReservationCount(resNum);
		
		/* 문의 수 조회 */
		int qaNum = 1;
		int newCSCnt = adminMapper.getCSCount(qaNum);
		qaNum = 2;
		int noAnswerCnt = adminMapper.getCSCount(qaNum);
		
		DashBoard dashboard = new DashBoard();
		dashboard.setNewMemberCnt(newMemberCnt);
		dashboard.setSecessionCnt(seceMemberCnt);
		dashboard.setTotalMemberCnt(totalMemberCnt);
		dashboard.setInternalCnt(internalCnt);
		dashboard.setSurgeryCnt(surgeryCnt);
		dashboard.setOphthalCnt(ophthalCnt);
		dashboard.setOrthoCnt(orthoCnt);
		dashboard.setNewCSCnt(newCSCnt);
		dashboard.setNoAnswerCnt(noAnswerCnt);
		
		return dashboard;
	}

	@Override
	public List<VisitCount> getVisitCount() {
		/* 방문자 수 조회 */
		// 오늘
		int num = 1;
		int todayCount = adminMapper.getVisitCount(num);  
		// 어제
		num = 2;
		int yesterdayCount = adminMapper.getVisitCount(num); 
		// 그제
		num = 3;
		int twoDaysAgoCount = adminMapper.getVisitCount(num); 
		// 삼일 전
		num = 4;
		int threeDaysAgoCount = adminMapper.getVisitCount(num); 
		// 사일 전
		num = 5;
		int fourDaysAgoCount = adminMapper.getVisitCount(num); 
			
		Calendar calendar = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
		
		String today = sdf.format(calendar.getTime());  // 오늘
		
		calendar.add(Calendar.DATE, -1);
		String yesterday = sdf.format(calendar.getTime());  // 어제
		
		calendar.add(Calendar.DATE, -1);
		String twoDaysAgo = sdf.format(calendar.getTime());  // 그제
		
		calendar.add(Calendar.DATE, -1);
		String threeDaysAgo = sdf.format(calendar.getTime());  // 삼일 전
		
		calendar.add(Calendar.DATE, -1);
		String fourDaysAgo = sdf.format(calendar.getTime());  // 사일 전
		
		List<VisitCount> vcList = new ArrayList<>();
		vcList.add(new VisitCount(today, todayCount));
		vcList.add(new VisitCount(yesterday, yesterdayCount));
		vcList.add(new VisitCount(twoDaysAgo, twoDaysAgoCount));
		vcList.add(new VisitCount(threeDaysAgo, threeDaysAgoCount));
		vcList.add(new VisitCount(fourDaysAgo, fourDaysAgoCount));
		
		return vcList;
	}


}
