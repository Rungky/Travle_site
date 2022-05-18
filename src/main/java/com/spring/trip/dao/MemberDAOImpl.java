package com.spring.trip.dao;

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
	public MemberDTO login(MemberDTO memberDTO) throws Exception{
		System.out.println("memberDAO  member_id : " + memberDTO.getMember_id() + " member_pw : " + memberDTO.getMember_pw());
		return sqlSession.selectOne("mapper.member.login", memberDTO);
	}

	@Override
	public MemberDTO idFind(MemberDTO memberDTO) {
		System.out.println("memberDAO  member_id : " + memberDTO.getMember_id());
		return sqlSession.selectOne("mapper.member.idFind", memberDTO);
	}
	
	//=========================================

	@Override
	public MemberDTO select_myMember(String member_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DormVO> selectList_likeDorm(String member_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeComment(String member_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeReview(String member_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeLike(String member_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeQuestion(String member_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMember(String member_id) {
		// TODO Auto-generated method stub
		
	}

}
