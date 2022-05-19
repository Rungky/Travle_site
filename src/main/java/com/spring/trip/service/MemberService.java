package com.spring.trip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.trip.dao.MemberDAO;
import com.spring.trip.dto.DormVO;
import com.spring.trip.dto.MemberDTO;

public interface MemberService {
	
	public MemberDTO login(MemberDTO memberDTO) throws Exception;
	
	public MemberDTO join(MemberDTO memberDTO) throws Exception;
	
	public MemberDTO idFind(MemberDTO memberDTO) throws Exception;
	
	public MemberDTO pwFind(MemberDTO memberDTO) throws Exception;
	
	public int newPw(MemberDTO memberDTO) throws Exception;
	
	public MemberDTO select_myMember(String member_id) throws Exception; // 마이페이지 표시
	
	public List<DormVO> selectList_likeDorm(String member_id) throws Exception; // 내가 좋아요한 숙소 보기 
	
	public void modifyMemberName(MemberDTO memberDTO) throws Exception;  //닉네임  수정
	
	public void modifyMemberPw(MemberDTO memberDTO) throws Exception;  //비밀번호 수정 
	
	// 회원 탈퇴 
	public void removeMember(String member_id) throws Exception; ;
	
}
