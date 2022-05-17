package com.spring.trip.dto;

import java.sql.Date;

public class QuestionDTO {
	private int question_no;
	private int question_parentno;
	private String question_title;
	private String question_contents;
	private String question_picture;
	private Date question_date;
	private int question_view;
	private String member_id;
	
	public int getQuestion_no() {
		return question_no;
	}
	public void setQuestion_no(int question_no) {
		this.question_no = question_no;
	}
	public int getQuestion_parentno() {
		return question_parentno;
	}
	public void setQuestion_parentno(int question_parentno) {
		this.question_parentno = question_parentno;
	}
	public String getQuestion_title() {
		return question_title;
	}
	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}
	public String getQuestion_contents() {
		return question_contents;
	}
	public void setQuestion_contents(String question_contents) {
		this.question_contents = question_contents;
	}
	public String getQuestion_picture() {
		return question_picture;
	}
	public void setQuestion_picture(String question_picture) {
		this.question_picture = question_picture;
	}
	public Date getQuestion_date() {
		return question_date;
	}
	public void setQuestion_date(Date question_date) {
		this.question_date = question_date;
	}
	public int getQuestion_view() {
		return question_view;
	}
	public void setQuestion_view(int question_view) {
		this.question_view = question_view;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
}
