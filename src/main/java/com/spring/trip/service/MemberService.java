package com.spring.trip.service;

import com.spring.trip.dto.MemberDTO;

public interface MemberService {
	public MemberDTO login(MemberDTO memberDTO) throws Exception;
	
	public MemberDTO join(MemberDTO memberDTO) throws Exception;
	
	public MemberDTO idFind(MemberDTO memberDTO) throws Exception;
}
