package com.spring.trip.dto;

public class DormDTO {
	private int dorm_no; 
	private String dorm_name; 
	private String dorm_contents; 
	private String dorm_addr; 
	private String dorm_picture; 
	private int like_cnt;
	private int opt_wifi;
	private int opt_parking;
	private int opt_aircon;
	private int opt_dryer;
	private int opt_port;
	private int dorm_category_no;
	
	private int review_count;
	private double scoreAvr;
	
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
	public String getDorm_contents() {
		return dorm_contents;
	}
	public void setDorm_contents(String dorm_contents) {
		this.dorm_contents = dorm_contents;
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
	public int getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
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
	public int getReview_count() {
		return review_count;
	}
	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}
	public double getScoreAvr() {
		return scoreAvr;
	}
	public void setScoreAvr(double scoreAvr) {
		this.scoreAvr = scoreAvr;
	}
}
