package com.spring.trip.dto;

public class RoomDTO {
	private int room_no;
	private int dorm_no; 
	private String room_name; 
	private String room_contents; 
	private String room_picture; 
	private int room_pay_day; 
	private int room_pay_night;
	private int room_person;
	
	private int reserved;
	
	
	
	public int getReserved() {
		return reserved;
	}
	public void setReserved(int reserved) {
		this.reserved = reserved;
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
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public String getRoom_contents() {
		return room_contents;
	}
	public void setRoom_contents(String room_contents) {
		this.room_contents = room_contents;
	}
	public String getRoom_picture() {
		return room_picture;
	}
	public void setRoom_picture(String room_picture) {
		this.room_picture = room_picture;
	}
	public int getRoom_pay_day() {
		return room_pay_day;
	}
	public void setRoom_pay_day(int room_pay_day) {
		this.room_pay_day = room_pay_day;
	}
	public int getRoom_pay_night() {
		return room_pay_night;
	}
	public void setRoom_pay_night(int room_pay_night) {
		this.room_pay_night = room_pay_night;
	}
	public int getRoom_person() {
		return room_person;
	}
	public void setRoom_person(int room_person) {
		this.room_person = room_person;
	} 
	
	
	
	
}
