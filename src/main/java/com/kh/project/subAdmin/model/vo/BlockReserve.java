package com.kh.project.subAdmin.model.vo;

import java.util.Date;

/* 예약 불가일 */
public class BlockReserve {
	
	private int block_no;		// 예약 불가 번호
	private Date block_date;	// 예약 불가일
	private int vno;			// 의료진 번호
	
	public BlockReserve() {}

	public BlockReserve(int block_no, Date block_date, int vno) {
		super();
		this.block_no = block_no;
		this.block_date = block_date;
		this.vno = vno;
	}

	public int getBlock_no() {
		return block_no;
	}

	public void setBlock_no(int block_no) {
		this.block_no = block_no;
	}

	public Date getBlock_date() {
		return block_date;
	}

	public void setBlock_date(Date block_date) {
		this.block_date = block_date;
	}

	public int getVno() {
		return vno;
	}

	public void setVno(int vno) {
		this.vno = vno;
	}

	@Override
	public String toString() {
		return "BlockReserve [block_no=" + block_no + ", block_date=" + block_date + ", vno=" + vno + "]";
	}
	
	

}
