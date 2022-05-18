package com.spring.trip.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.trip.dto.DormVO;
import com.spring.trip.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void join(MemberDTO memberDTO) {
		sqlSession.insert("mapper.member.join", memberDTO);
	}

	@Override
	public MemberDTO login(MemberDTO memberDTO) throws Exception {
		System.out.println(
				"memberDAO  member_id : " + memberDTO.getMember_id() + " member_pw : " + memberDTO.getMember_pw());
		return sqlSession.selectOne("mapper.member.login", memberDTO);
	}

	@Override
	public MemberDTO idFind(MemberDTO memberDTO) {
		System.out.println("memberDAO  member_id : " + memberDTO.getMember_id());
		return sqlSession.selectOne("mapper.member.idFind", memberDTO);
	}

	// =========================================

	@Override
	public MemberDTO select_myMember(String member_id) {
		MemberDTO memberDTO = sqlSession.selectOne("mapper.member.select_myMember", member_id);
		return memberDTO;
	}

	@Override
	public List<DormVO> selectList_likeDorm(String member_id) {
		List<DormVO> list = new ArrayList<DormVO>();
		list = sqlSession.selectList("mapper.member.selectList_likeDorm", member_id);
		return list;
	}

	@Override
	public void modifyMemberName(String member_id, String member_name) {
		sqlSession.update("mapper.member.modifyMemberName", member_name);

	}

	@Override
	public void modifyMemberPw(String member_id, String member_pw) {
		sqlSession.update("mapper.member.modifyMemberPw", member_pw);

	}

	// =========회원탈퇴=============
	@Override
	public void removeComment(String member_id) {
		sqlSession.delete("mapper.member.removeComment", member_id);

	}

	@Override
	public void removeReview(String member_id) {
		sqlSession.delete("mapper.member.removeReview", member_id);

	}
	
	@Override
	public void removeReservation(String member_id) {
		sqlSession.delete("mapper.member.removeReservation", member_id);
		
	}

	@Override
	public void removeLike(String member_id) {
		sqlSession.delete("mapper.member.removeLike", member_id);
	}

	@Override
	public void removeQuestion(String member_id) {
		sqlSession.delete("mapper.memberer.removeQuestion", member_id);
	}

	@Override
	public void removeMember(String member_id) {
		sqlSession.delete("mapper.member.removeMember", member_id);
	}



}
