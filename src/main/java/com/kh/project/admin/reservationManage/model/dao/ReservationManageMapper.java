package com.kh.project.admin.reservationManage.model.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.kh.project.admin.common.model.vo.Search;
import com.kh.project.admin.reservationManage.model.vo.Dog;
import com.kh.project.admin.reservationManage.model.vo.ReservationManage;

@Mapper
public interface ReservationManageMapper {

	List<ReservationManage> selectReservationList(Search search);

	Dog selectInputDog(int reservationNo);

	Dog selectDog(int reservationNo);

	ReservationManage selectReservationByNo(int no);

	int modifyReservation(ReservationManage reservation);

	int modifyInputDog(Dog dog);

	int deleteReservation(int reservationNo);

	int deleteInputDog(int reservationNo);

	int getListCount(Search search);


}
