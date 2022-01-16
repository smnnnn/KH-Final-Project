package com.kh.project.admin.adminManage.model.service;

import java.util.List;

import com.kh.project.admin.adminManage.model.vo.DashBoard;
import com.kh.project.admin.memberManage.model.vo.MemberInfo;

public interface AdminService {

	List<MemberInfo> selectAdminList(int startRow, int endRow);

	int getListCount();

	MemberInfo selectAdminByNo(int no);

	int modifyAdminAuthority(int no, int authority);

	int deleteAdmin(int adminNo);

	int registAdmin(MemberInfo admin);

	int modifyAdminInfo(MemberInfo changeInfo);

	DashBoard dashBoard();

}
