package com.kh.project.admin.memberManage.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.project.admin.memberManage.model.vo.MemberInfo;
import com.kh.project.admin.reservationManage.model.vo.Dog;

@Mapper
public interface MemberManageMapper {

	List<MemberInfo> selectMemberList();

	Dog selectDogInfo(int no);

}
