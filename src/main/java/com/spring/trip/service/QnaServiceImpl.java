package com.spring.trip.service;

import java.util.ArrayList;
import java.util.List;

import com.spring.trip.dao.TripDAOImpl;
import com.spring.trip.dto.QuestionDTO;



public class QnaServiceImpl {
//	TripDAOImpl tripDAO;
//	
//	public QnaServiceImpl() {
//		tripDAO = new TripDAOImpl();
//	}
//	
//	List recursive(int pId, List list) {
//		List resultList = new ArrayList();
//		
//		for(int i=0; i<list.size(); i++) {
//			
//			QuestionDTO dto = (QuestionDTO)list.get(i);
//			if(dto.getQuestion_parentno() == pId) {
//				resultList.add(dto);
//
//				List tempList = recursive(dto.getQuestion_no(), list);
//				resultList.add( tempList );
//				
//			}
//		}
//		
//		return resultList;
//	}
//	
//	public int getTotal() {
//		return tripDAO.selectTotalQuestion();
//	}
//	
////	public List<QuestionDTO> listArticles(){
////		List<QuestionDTO> articlesList = tripDAO.selectAllQuestion();
////		
////		return articlesList;
////	}
//	//부모 없는글
//	public List<QuestionDTO> listArticles(String id){
//		List<QuestionDTO> articlesList = tripDAO.selectMemberQuestion(id);
//		
//		return articlesList;
//	}
//	//답변글
//	public List<QuestionDTO> listAnswers(){
//		List<QuestionDTO> answersList = tripDAO.selectAnswer();
//		
//		return answersList;
//	}
//	
//	public void addArticle(QuestionDTO questionDTO) {
//		tripDAO.insertNewQuestion(questionDTO);
//	}
//	public void addReply(QuestionDTO questionDTO) {
//		tripDAO.insertReplyQuestion(questionDTO);
//	}
//	
//	public List<QuestionDTO> listQna(int question_no){
//		List<QuestionDTO> QuestionList = tripDAO.selectQuestion(question_no);
//		
//		return QuestionList;
//	}
//	
//	public List<QuestionDTO> ReplyQna(){
//		List<QuestionDTO> QuestionList = tripDAO.selectReply();
//		
//		return QuestionList;
//	}
//	
//	public void modArticle(QuestionDTO questionDTO) {
//		tripDAO.updateArticle(questionDTO);
//	}
//	
//	public List<QuestionDTO> listMod(int question_no){
//		List<QuestionDTO> QuestionList = tripDAO.selectmodQuestion(question_no);
//		
//		return QuestionList;
//	}
//	
//	public void removeArticle(int question_no) {
//		tripDAO.deleteArticle(question_no);
//	}
}
