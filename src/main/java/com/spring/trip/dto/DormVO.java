package com.spring.trip.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DormVO {
	
	private int dorm_no; 
	private String dorm_name;
	private String dorm_addr; 
	private String dorm_picture; 
	private Date date_s;
	private Date date_e;
	private int opt_wifi;
	private int opt_parking;
	private int opt_aircon;
	private int opt_dryer;
	private int opt_port;
	private int dorm_category_no;
	private int min_pay_night;
	private int count_reserve_no;
	private int room_persion;
	private String search;
	private int cnt_room;
	public int getCnt_room() {
		return cnt_room;
	}
	public void setCnt_room(int cnt_room) {
		this.cnt_room = cnt_room;
	}
	private int cnt_rno;
	
	
	

	public int getCnt_rno() {
		return cnt_rno;
	}
	public void setCnt_rno(int cnt_rno) {
		this.cnt_rno = cnt_rno;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public Date getDate_s() {
		return date_s;
	}
	public void setDate_s(Date date_s) {
		this.date_s = date_s;
	}
	public Date getDate_e() {
		return date_e;
	}
	public void setDate_e(Date date_e) {
		this.date_e = date_e;
	}
	public int getRoom_persion() {
		return room_persion;
	}
	public void setRoom_persion(int room_persion) {
		this.room_persion = room_persion;
	}
	public int getMin_pay_night() {
		return min_pay_night;
	}
	public void setMin_pay_night(int min_pay_night) {
		this.min_pay_night = min_pay_night;
	}
	public int getCount_reserve_no() {
		return count_reserve_no;
	}
	public void setCount_reserve_no(int count_reserve_no) {
		this.count_reserve_no = count_reserve_no;
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
	public String getDorm_addr() {
		return dorm_addr;
	}
	public void setDorm_addr(String dorm_addr) {
		this.dorm_addr = dorm_addr;
	}
	public String getDorm_picture() {
		return dorm_picture;
	}
	public void setDorm_picture(String dorm_picture) {
		this.dorm_picture = dorm_picture;
	}
	public int getOpt_wifi() {
		return opt_wifi;
	}
	public void setOpt_wifi(int opt_wifi) {
		this.opt_wifi = opt_wifi;
	}
	public int getOpt_parking() {
		return opt_parking;
	}
	public void setOpt_parking(int opt_parking) {
		this.opt_parking = opt_parking;
	}
	public int getOpt_aircon() {
		return opt_aircon;
	}
	public void setOpt_aircon(int opt_aircon) {
		this.opt_aircon = opt_aircon;
	}
	public int getOpt_dryer() {
		return opt_dryer;
	}
	public void setOpt_dryer(int opt_dryer) {
		this.opt_dryer = opt_dryer;
	}
	public int getOpt_port() {
		return opt_port;
	}
	public void setOpt_port(int opt_port) {
		this.opt_port = opt_port;
	}
	public int getDorm_category_no() {
		return dorm_category_no;
	}
	public void setDorm_category_no(int dorm_category_no) {
		this.dorm_category_no = dorm_category_no;
	}
	
	
	
}
