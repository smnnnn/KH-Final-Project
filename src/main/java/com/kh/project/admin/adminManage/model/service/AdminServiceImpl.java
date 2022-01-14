package com.kh.project.admin.adminManage.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.project.admin.adminManage.model.dao.AdminMapper;
import com.kh.project.admin.memberManage.model.vo.MemberInfo;
import com.kh.project.member.model.vo.MemberRole;

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
		admin.setAddress("서울특별시 강남구 테헤란로 10길 9 그랑프리빌딩 4F, 5F, 7F");		
		int result = adminMapper.registAdmin(admin);
		
		MemberRole memberRole = new MemberRole(); 
		memberRole.setAuthorityCode(1); 
		MemberRole memberRole2 = new MemberRole(); 
		memberRole2.setAuthorityCode(2); 
		
		int baseRole = adminMapper.registBaseRole(memberRole);
		int role = adminMapper.registAdminRole(memberRole2);
				
		return result > 0 && baseRole > 0 && role > 0 ? 1 : 0;
	}


}
