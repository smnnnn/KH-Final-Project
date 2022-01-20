package com.kh.project.subAdmin.model.service;

import java.util.List;

import com.kh.project.subAdmin.model.vo.Holiday;
import com.kh.project.subAdmin.model.vo.Veterinarian;
import com.kh.project.subAdmin.model.vo.VeterinarianAndHoliday;

public interface VeterinarianService {
	
	/* 각 진료 과목 당 의료진 1명씩의 배치가 기본이므로 의료진이 새로 등록되면 해당 진료 과목의 이전 의료진 상태는 N으로 변경됨 */
	int modifyStatusVeterinarian(int tno);

	/* 의료진 등록-> 휴무일 테이블에도 값이 들어가야 함 */
	int registVeterinarian(Veterinarian newVeterinarian);
	int registHoliday(Holiday holiday);

	/* 의료진 검색 */
	List<VeterinarianAndHoliday> findVeterinarian(String vname);

	/* 의료진 정보 수정 */
	int modifyVeterinarian(Veterinarian veterinarianModify);
	int modifyHoliday(Holiday holiday);

	

}
