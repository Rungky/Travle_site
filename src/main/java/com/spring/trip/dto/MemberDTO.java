package com.spring.trip.dto;

public class MemberDTO {
	private String member_id;
	private String member_pw;
	private String member_names;
	private String member_tel;
	private String member_authority;
	
	@Override
	public String toString() {
		return "MemberDTO [member_id=" + member_id + ", member_pw=" + member_pw + ", member_names=" + member_names
				+ ", member_tel=" + member_tel + ", member_authority=" + member_authority + "]";
	}
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_names() {
		return member_names;
	}
	public void setMember_names(String member_names) {
		this.member_names = member_names;
	}
	public String getMember_tel() {
		return member_tel;
	}
	public void setMember_tel(String member_tel) {
		this.member_tel = member_tel;
	}
	public String getMember_authority() {
		return member_authority;
	}
	public void setMember_authority(String member_authority) {
		this.member_authority = member_authority;
	}
	
	
	

	

}
