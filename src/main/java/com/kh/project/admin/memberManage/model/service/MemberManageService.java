package com.kh.project.admin.memberManage.model.service;

import java.util.List;

import com.kh.project.admin.memberManage.model.vo.MemberInfo;

public interface MemberManageService {

	List<MemberInfo> selectMemberList();

	MemberInfo selectMemberByNo(int no);

}
