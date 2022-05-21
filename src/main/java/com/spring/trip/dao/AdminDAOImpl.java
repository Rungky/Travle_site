package com.spring.trip.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.trip.dto.DormDTO;
import com.spring.trip.dto.MemberDTO;


@Repository
public class AdminDAOImpl implements AdminDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<MemberDTO> allMembers() {
		return sqlSession.selectList("mapper.admin.allMembers");
	}
	
	@Override
	public void adminMember(MemberDTO dto) {
		sqlSession.update("mapper.admin.adminMember",dto);
	}
	
	@Override
	public List<DormDTO> allDormsList(){
		List<DormDTO> dormsList = sqlSession.selectList("mapper.admin.allDorm");
		return dormsList;
	};
	
	@Override
	public void adminDorm(DormDTO dto) {
		sqlSession.update("mapper.admin.adminDorm",dto);
	}
	
	@Override
	public int checkDormno(int dormno) {
		return sqlSession.selectOne("mapper.admin.dormnoChecking",dormno);
	};
	
	@Override
	public void adminDormInsert(DormDTO dto) {
		sqlSession.insert("mapper.admin.adminDormInsert",dto);
	}

}
