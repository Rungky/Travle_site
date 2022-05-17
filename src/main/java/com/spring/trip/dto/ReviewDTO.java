package com.spring.trip.dto;

import java.sql.Date;

public class ReviewDTO {
	private int review_no;
	private String review_title;
	private String review_contents;
	private Double review_score;
	private Date review_date;
	private String review_picture;
	private int reserve_no; 
	private String member_id;
	private int score; 
	
	
	
	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}
	public String getReview_title() {
		return review_title;
	}
	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}
	public String getReview_contents() {
		return review_contents;
	}
	public void setReview_contents(String review_contents) {
		this.review_contents = review_contents;
	}
	public Date getReview_date() {
		return review_date;
	}
	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}
	public String getReview_picture() {
		return review_picture;
	}
	public void setReview_picture(String review_picture) {
		this.review_picture = review_picture;
	}
	public int getReserve_no() {
		return reserve_no;
	}
	public void setReserve_no(int reserve_no) {
		this.reserve_no = reserve_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Double getReview_score() {
		return review_score;
	}
	public void setReview_score(Double review_score) {
		this.review_score = review_score;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
