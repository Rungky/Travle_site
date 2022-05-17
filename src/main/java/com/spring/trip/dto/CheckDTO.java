package com.spring.trip.dto;

import java.sql.Date;

public class CheckDTO {
	private int dorm_no;
	private int room_no;
	private String dorm_name;
	private String room_name;
	private Date reserve_checkin;
	private Date reserve_checkout;
	private int reserve_pay;

	
	public Date getReserve_checkin() {
		return reserve_checkin;
	}
	public void setReserve_checkin(Date reserve_checkin) {
		this.reserve_checkin = reserve_checkin;
	}
	public Date getReserve_checkout() {
		return reserve_checkout;
	}
	public void setReserve_checkout(Date reserve_checkout) {
		this.reserve_checkout = reserve_checkout;
	}
	public int getReserve_pay() {
		return reserve_pay;
	}
	public void setReserve_pay(int reserve_pay) {
		this.reserve_pay = reserve_pay;
	}
	public int getRoom_no() {
		return room_no;
	}
	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}
	public int getDorm_no() {
		return dorm_no;
	}
	public void setDorm_no(int dorm_no) {
		this.dorm_no = dorm_no;
	}
	public String getDorm_name() {
		return dorm_name;
	}
	public void setDorm_name(String dorm_name) {
		this.dorm_name = dorm_name;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	
	
	
	
}
