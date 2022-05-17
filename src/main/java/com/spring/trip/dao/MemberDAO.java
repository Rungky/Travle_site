package com.spring.trip.dao;

import com.spring.trip.dto.MemberDTO;

public interface MemberDAO {
	public void join(MemberDTO memberDTO);  //회원가입
	
	public MemberDTO login(MemberDTO memberDTO) throws Exception;  //로그인
	
	public MemberDTO idFind(MemberDTO memberDTO);  //아이디 찾기
	}
