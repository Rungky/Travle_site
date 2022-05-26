package com.spring.trip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip.dao.AdminDAO;
import com.spring.trip.dto.DormDTO;
import com.spring.trip.dto.MemberDTO;
import com.spring.trip.dto.QuestionDTO;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminDAO adminDAO;
	
	@Override
	public List<MemberDTO> allMembers() {
		List<MemberDTO> list = adminDAO.allMembers();
		return list;
	}

	@Override
	public void adminMember(MemberDTO dto) {
		 adminDAO.adminMember(dto);
	}

	@Override
	public List<DormDTO> allDormsList() {
		List<DormDTO> list = adminDAO.allDormsList();
		return list;
	}

	@Override
	public void adminDorm(DormDTO dto) {
		adminDAO.adminDorm(dto);
	}

	@Override
	public int checkDormno(int dormno) {
		return adminDAO.checkDormno(dormno);
	}

	@Override
	public void adminDormInsert(DormDTO dto) {
		adminDAO.adminDormInsert(dto);
	}

	@Override
	public void adminDelDorm(int dormno) {
		adminDAO.adminDelDorm(dormno);
	}

	@Override
	public List<QuestionDTO> allQuestion() {
		List<QuestionDTO> list = adminDAO.allQuestion();
		return list;
	}

	@Override
	public void admindeleteArticle(int question_no) {
		adminDAO.admindeleteArticle(question_no);
		
	}

	@Override
	public List<QuestionDTO> adminselectQuestion(int question_no) {
		List<QuestionDTO> QuestionList = adminDAO.adminselectQuestion(question_no);
		
		return QuestionList;
	}

	@Override
	public void admininsertReplyQuestion(QuestionDTO questionDTO) {
		adminDAO.admininsertReplyQuestion(questionDTO);
	}

	@Override
	public int countQuestion() {
		return adminDAO.countQuestion();
	}

	@Override
	public List<QuestionDTO> adminselectAllQuestion(int parentno) {
		List<QuestionDTO> QuestionList = adminDAO.adminselectAllQuestion(parentno);		
		return QuestionList;
	}
	@Override
	public List<QuestionDTO> adminselectmodReply(int question_no) {
		List<QuestionDTO> answerList = adminDAO.adminselectmodReply(question_no);
		
		return answerList;
	}
	@Override
	public void adminupdateReply(QuestionDTO questionDTO) {
		adminDAO.adminupdateReply(questionDTO);	
	}
	
	@Override
	public void admindeleteReply(int question_no) {
		adminDAO.admindeleteReply(question_no);
	}

	
	

}
