package com.spring.trip.dto;

import java.sql.Date;

public class LikeDTO {
	private int like_no; 
	private Date like_date; 
	private String member_id; 
	private int dorm_no;
	
	
	public int getLike_no() {
		return like_no;
	}
	public void setLike_no(int like_no) {
		this.like_no = like_no;
	}
	public Date getLike_date() {
		return like_date;
	}
	public void setLike_date(Date like_date) {
		this.like_date = like_date;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getDorm_no() {
		return dorm_no;
	}
	public void setDorm_no(int dorm_no) {
		this.dorm_no = dorm_no;
	} 

}
