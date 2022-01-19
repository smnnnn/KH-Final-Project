package com.kh.project.admin.adminManage.model.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.project.admin.memberManage.model.vo.MemberInfo;
import com.kh.project.admin.visit.model.vo.Visit;
import com.kh.project.member.model.vo.MemberRole;

@Mapper
public interface AdminMapper {

	List<MemberInfo> selectAdminList(Map<String, Object> map);

	int getListCount();

	List<MemberRole> selectAdminRole(int no);

	MemberInfo selectAdminByNo(int no);

	int deleteAdminAuthorityOne(int no, int authority);

	int insertAdminAuthority(int no, int authority);

	int deleteAdmin(int adminNo);

	int deleteAdminAuthority(int adminNo);

	int registAdmin(MemberInfo admin);

	int registBaseRole(MemberRole memberRole);

	int registAdminRole(MemberRole memberRole2);

	int modifyAdminInfo(MemberInfo changeInfo);

	int getMemberCount(int num);

	int getReservationCount(int resNum);

	int getCSCount(int qaNum);

	int insertVisitor(Visit vc);

	int getVisitCount(int num);


}
