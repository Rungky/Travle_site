package com.spring.trip.dto;

import java.sql.Date;

public class CommentDTO {
	private int comment_no;
	private String comment_contents;
	private Date comment_Date;
	private int comment_parentno;
	private String member_id;
	private int review_no;
	
	
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public String getComment_contents() {
		return comment_contents;
	}
	public void setComment_contents(String comment_contents) {
		this.comment_contents = comment_contents;
	}
	public Date getComment_Date() {
		return comment_Date;
	}
	public void setComment_Date(Date comment_Date) {
		this.comment_Date = comment_Date;
	}
	public int getComment_parentno() {
		return comment_parentno;
	}
	public void setComment_parentno(int comment_parentno) {
		this.comment_parentno = comment_parentno;
	}
	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
}
