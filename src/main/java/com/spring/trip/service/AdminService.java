package com.spring.trip.service;

import java.util.List;

import com.spring.trip.dto.DormDTO;
import com.spring.trip.dto.MemberDTO;
import com.spring.trip.dto.QuestionDTO;

public interface AdminService {
	public List<MemberDTO> allMembers();
	public void adminMember(MemberDTO dto);
	public List<DormDTO> allDormsList();
	public void adminDorm(DormDTO dto);
	public int checkDormno(int dormno);
	public void adminDormInsert(DormDTO dto);
	public void adminDelDorm(int dormno);
	public List<QuestionDTO> allQuestion();
	public void admindeleteArticle(int question_no);
	public List<QuestionDTO> adminselectQuestion(int question_no);
	public void admininsertReplyQuestion (QuestionDTO questionDTO);
	public int countQuestion();
}
