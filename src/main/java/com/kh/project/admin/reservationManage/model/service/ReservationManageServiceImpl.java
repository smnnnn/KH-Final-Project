package com.kh.project.admin.reservationManage.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.project.admin.reservationManage.model.vo.Dog;
import com.kh.project.admin.reservationManage.model.vo.ReservationManage;
import com.kh.project.admin.reservationManage.model.dao.ReservationManageMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReservationManageServiceImpl implements ReservationManageService {
	
	private ReservationManageMapper reservationManageMapper;
	
	@Autowired
	public ReservationManageServiceImpl(ReservationManageMapper reservationManageMapper) {
		this.reservationManageMapper = reservationManageMapper;
	}

	@Override
	public List<ReservationManage> selectReservationList() {
		
		List<ReservationManage> reservationList = reservationManageMapper.selectReservationList();

		for(ReservationManage reservation : reservationList) {
			if(reservation.getDogNo() == 0) {
				Dog dog = reservationManageMapper.selectInputDog(reservation.getReservationNo());
				reservation.setDog(dog);
			} else {
				Dog dog = reservationManageMapper.selectDog(reservation.getReservationNo());
				reservation.setDog(dog);
			}
		}
		
		return reservationList;
	}

	@Override
	public ReservationManage selectReservationByNo(int no) {
		
		ReservationManage reservation = reservationManageMapper.selectReservationByNo(no);
		
		if(reservation.getDogNo() == 0) {
			Dog dog = reservationManageMapper.selectInputDog(no);
			reservation.setDog(dog);
		} else {
			Dog dog = reservationManageMapper.selectDog(no);
			reservation.setDog(dog);
		}
		
		return reservation;
	}

	@Transactional
	@Override
	public int modifyReservation(ReservationManage reservation) {
		
		int reservationResult = reservationManageMapper.modifyReservation(reservation);
		
		int dogResult;
		if(reservation.getDogNo() == 0) {
			reservation.getDog().setReservationNo(reservation.getReservationNo());
			dogResult = reservationManageMapper.modifyInputDog(reservation.getDog());
		} else {
			dogResult = 1;
		}
		
		return reservationResult > 0 && dogResult > 0 ? 1 : 0;
	}

	@Transactional
	@Override
	public int deleteReservation(int reservationNo, int dogNo) {
		
		int dogResult;
		if(dogNo == 0) {
			dogResult = reservationManageMapper.deleteInputDog(reservationNo);
		} else {
			dogResult = 1;
		}
		
		int result = reservationManageMapper.deleteReservation(reservationNo);
		
		
		return result > 0 && dogResult > 0 ? 1 : 0;
	}

}
