package com.spring.trip.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.trip.dto.DormDTO;
import com.spring.trip.dto.MemberDTO;
import com.spring.trip.dto.QuestionDTO;


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

	@Override
	public void adminDelDorm(int dormno) {
		sqlSession.delete("mapper.admin.adminDormDelete", dormno);
	}

	@Override
	public List<QuestionDTO> allQuestion() {
		List<QuestionDTO> questionList = sqlSession.selectList("mapper.admin.allQuestion");
		return questionList;
	}

	@Override
	public void admindeleteArticle(int question_no) {
		sqlSession.delete("mapper.admin.admindeleteArticle", question_no);
	}

	@Override
	public List<QuestionDTO> adminselectQuestion(int question_no) {
		List<QuestionDTO> QuestionList = new ArrayList();
		QuestionList =  sqlSession.selectList("mapper.admin.adminselectQuestion", question_no);
		
		return QuestionList;
	}

	@Override
	public void admininsertReplyQuestion(QuestionDTO questionDTO) {
		sqlSession.insert("mapper.admin.admininsertReplyQuestion", questionDTO);
	}

	@Override
	public int countQuestion() {
		int count = sqlSession.selectOne("mapper.admin.countQuestion");
		return count;
	}


}
