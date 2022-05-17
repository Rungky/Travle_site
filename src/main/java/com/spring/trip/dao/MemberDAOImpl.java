package com.spring.trip.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
