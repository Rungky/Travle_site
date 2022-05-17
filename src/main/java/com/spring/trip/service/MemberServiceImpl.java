package com.spring.trip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip.dao.MemberDAO;
import com.spring.trip.dto.MemberDTO;

@Service("memberServiceImpl")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public MemberDTO login(MemberDTO memberDTO) throws Exception{
		System.out.println("memberService    member_id : " + memberDTO.getMember_id() + " member_pw : " + memberDTO.getMember_pw());
		return memberDAO.login(memberDTO);
	}

	@Override
	public MemberDTO join(MemberDTO memberDTO) throws Exception {
		memberDAO.join(memberDTO);
		return memberDTO;
	}

	@Override
	public MemberDTO idFind(MemberDTO memberDTO) throws Exception {
		System.out.println("idFind member_id : " + memberDTO.getMember_id());
		return memberDAO.idFind(memberDTO);
	}

}
