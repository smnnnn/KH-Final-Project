package com.kh.project.subAdmin.model.service;

import com.kh.project.subAdmin.model.vo.Holiday;
import com.kh.project.subAdmin.model.vo.Veterinarian;

public interface VeterinarianService {
	
	/* select~update, insert */
	
	// 의료진 등록-> 휴무일 테이블에도 값이 들어가야 함
	int registVeterinarian(Veterinarian newVeterinarian);
	int registHoliday(Holiday holiday);

	

}
