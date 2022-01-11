package com.kh.project.admin.memberManage.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.project.admin.memberManage.model.dao.MemberManageMapper;
import com.kh.project.admin.memberManage.model.vo.MemberInfo;
import com.kh.project.admin.reservationManage.model.vo.Dog;

@Service
public class MemberManageServiceImpl implements MemberManageService{

	private MemberManageMapper memberManageMapper;
	
	@Autowired
	public MemberManageServiceImpl(MemberManageMapper memberManageMapper) {
		this.memberManageMapper = memberManageMapper;
	}
	
	@Override
	public List<MemberInfo> selectMemberList() {
		
		List<MemberInfo> memberInfo = memberManageMapper.selectMemberList();
		List<MemberInfo> returnMemberInfo = new ArrayList<>();
		for(MemberInfo member : memberInfo) {
			if(member.getMemberRoleList().size() == 1) {
				Dog dog = memberManageMapper.selectDogInfo(member.getNo());
				member.setDog(dog);
				returnMemberInfo.add(member);
			}
		}
		
		return returnMemberInfo;
	}

}
