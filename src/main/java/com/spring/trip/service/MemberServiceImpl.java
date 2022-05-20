package com.spring.trip.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip.dao.MemberDAO;
import com.spring.trip.dto.DormVO;
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
	
	@Override
	public MemberDTO pwFind(MemberDTO memberDTO) throws Exception {
		return memberDAO.pwFind(memberDTO);
	}

	@Override
	public int newPw(MemberDTO memberDTO) throws Exception {
		return memberDAO.newPw(memberDTO);
	}
	
	//=================
	@Override
	public MemberDTO select_myMember(String member_id) throws Exception {
		MemberDTO  memberDTO = memberDAO.select_myMember(member_id);
		return memberDTO;
	}

	@Override
	public List<DormVO> selectList_likeDorm(String member_id) throws Exception {
		// TODO Auto-generated method stub
		List <DormVO> list = new ArrayList();
		list = memberDAO.selectList_likeDorm(member_id);
		return list;
	}

	@Override
	public void removeMember(String member_id) throws Exception {
		// TODO Auto-generated method stub
		memberDAO.removeComment(member_id);
		memberDAO.removeReview(member_id);
		memberDAO.removeReservation(member_id);
		memberDAO.removeLike(member_id);
		memberDAO.removeQuestion(member_id);
		memberDAO.removeMember(member_id);
	}

	@Override
	public void modifyMember(MemberDTO memberDTO) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("2  modifyMember  실행 직전 ");
		memberDAO.modifyMember(memberDTO);
	}
	
	
	@Override
	public void modifyMemberName(MemberDTO memberDTO) {
		memberDAO.modifyMemberName(memberDTO);
	}

	@Override
	public void modifyMemberPw(MemberDTO memberDTO) {
		memberDAO.modifyMemberPw(memberDTO);
	}

	


}
