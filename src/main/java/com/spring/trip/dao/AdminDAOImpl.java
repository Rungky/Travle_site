package com.spring.trip.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.trip.dto.DormDTO;
import com.spring.trip.dto.MemberDTO;
import com.spring.trip.dto.QuestionDTO;
import com.spring.trip.dto.RoomDTO;
import com.spring.trip.dto.RoomVO;


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
	public List<RoomVO> allRoomsList() {
		List<RoomVO> list = sqlSession.selectList("mapper.admin.allRoom");
		return list;
	}
	
	@Override
	public void adminRoom(RoomDTO roomDTO) {
		System.out.println("adminDAO : adminRoom 진입");
		sqlSession.update("mapper.admin.adminRoom", roomDTO);
	}

	@Override
	public void adminRoomInsert(RoomDTO roomDTO) {
		sqlSession.insert("mapper.admin.adminRoomInsert", roomDTO);
	}

	@Override
	public void adminRoomDelete(int room_no) {
		sqlSession.delete("mapper.admin.adminRoomDelete", room_no);
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

	@Override
	public List<QuestionDTO> adminselectAllQuestion(int parentno) {
		List<QuestionDTO> QuestionList = new ArrayList();
		QuestionList =  sqlSession.selectList("mapper.admin.selectAllQuestion", parentno);
		
		return QuestionList;
	}

	@Override
	public List<QuestionDTO> adminselectmodReply(int question_no) {
		List<QuestionDTO> answerList = new ArrayList();
		answerList =  sqlSession.selectList("mapper.admin.selectmodReply", question_no);
		
		return answerList;
	}

	@Override
	public void adminupdateReply(QuestionDTO questionDTO) {
		sqlSession.update("mapper.admin.updateReply", questionDTO);
		
	}

	@Override
	public void admindeleteReply(int question_no) {
		sqlSession.delete("mapper.admin.deleteReply", question_no);
		
	}

	


}
