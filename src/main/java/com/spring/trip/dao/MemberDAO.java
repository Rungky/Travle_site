package com.spring.trip.dao;

import java.util.List;

import com.spring.trip.dto.DormVO;
import com.spring.trip.dto.MemberDTO;

public interface MemberDAO {
	
	public void join(MemberDTO memberDTO);  //회원가입
	
	public MemberDTO login(MemberDTO memberDTO) throws Exception;  //로그인
	
	public MemberDTO idFind(MemberDTO memberDTO);  //아이디 찾기
	
	public MemberDTO select_myMember(String member_id); // 마이페이지 표시
	
	public List<DormVO> selectList_likeDorm(String member_id); // 내가 좋아요한 숙소 보기 
	
	// 회원 탈퇴 
	public void removeComment(String member_id);
	public void removeReview(String member_id);
	public void removeLike(String member_id);
	public void removeQuestion(String member_id);
	public void removeMember(String member_id);
	
	
}
