package com.spring.trip.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip.dao.TripDAO;
import com.spring.trip.dto.CheckDTO;
import com.spring.trip.dto.DormDTO;
import com.spring.trip.dto.DormVO;
import com.spring.trip.dto.MemberDTO;
import com.spring.trip.dto.PaymentDTO;
import com.spring.trip.dto.QuestionDTO;
import com.spring.trip.dto.ReservationDTO;
import com.spring.trip.dto.ReviewDTO;
import com.spring.trip.dto.RoomDTO;
import com.spring.trip.mapper.TripMapper;

@Service
public class TripServiceImpl implements TripService {
	
	@Autowired
	private TripDAO tripDAO;
	

	@Override
	public DormDTO selectDorm(int dormNo) {
		DormDTO dto = tripDAO.selectDorm(dormNo);
		return dto;
	}
	@Override
	public DormDTO selectDorm(String contents, String addr) {
		DormDTO dto = tripDAO.selectDorm(contents,addr);
		return dto;
	}
	@Override
	public void changeLike(int dormNo, int num) {
		tripDAO.changeLike(dormNo, num);
	}

	@Override
	public boolean checkLike(int dormNo, String id) {
		boolean like_bl = tripDAO.checkLike(dormNo, id);
		return like_bl;
	}

	@Override
	public void insertLike(int dormNo, String id) {
		tripDAO.insertLike(dormNo, id);
	}

	@Override
	public void deleteLike(int dormNo, String id) {
		tripDAO.deleteLike(dormNo, id);
	}

	@Override
	public List<RoomDTO> selectRoomsList(int dormNo) {
		List <RoomDTO> roomsList = tripDAO.selectRoomsList(dormNo);
		return roomsList;
	}

	@Override
	public List<RoomDTO> reservedRoomsList(int dormNo, Date checkIn, Date checkOut) {
		List<RoomDTO> reservedList = tripDAO.reservedRoomsList(dormNo, checkIn, checkOut);
		return reservedList;
	}

	@Override
	public List<ReviewDTO> selectReviewsList(int dormNo) {
		List<ReviewDTO> reviewsList = tripDAO.selectReviewsList(dormNo);
		return reviewsList;
	}

	@Override
	public ReservationDTO selectReservation(int reserveno) {
		ReservationDTO dto = tripDAO.selectReservation(reserveno);
		return dto;
	}

	@Override
	public List<ReservationDTO> selectReservationsList(String member) {
		List<ReservationDTO> list = tripDAO.selectReservationsList(member);
		return list;
	}

	@Override
	public int reserDelete(int reserve_no) {
		int rs = tripDAO.reserDelete(reserve_no);
		System.out.println("서비스에서 나온 rs"+ rs);
		return rs;
	}

	@Override
	public CheckDTO checkList(int dorm_no, int room_no, String dorm_name, String room_name, Date reserve_checkin,
			Date reserve_checkout, int reserve_pay, String in_time, String out_time) {
		CheckDTO dto = tripDAO.checkList(dorm_no, room_no, dorm_name, room_name, reserve_checkin, reserve_checkout, reserve_pay, in_time, out_time);
		return dto;
	}

	@Override
	public void insertReview(String title, String contents, double reviewScore, Date date, String picture,
			int reserveNo, String memberId) {
		tripDAO.insertReview(title, contents, reviewScore, date, picture, reserveNo, memberId);
	}

	@Override
	public int selectTotalQuestion() {
		return 0;
	}

	@Override
	public void insertReservation(String member, Date reserve_checkin, Date reserve_checkout, int reserve_pay,
			int room_no, int dorm_no, long pay_no, int pay_check,String in_time, String out_time) {
		
		tripDAO.insertReservation(member, reserve_checkin, reserve_checkout, reserve_pay, room_no ,dorm_no, pay_no, pay_check, in_time, out_time);
		System.out.println("서비스쪽 이제 다오 메소드 들어가는중");
	}

	@Override
	public MemberDTO memberDto(String member_id) {
		MemberDTO dto = tripDAO.memberDto(member_id);
		return dto;
	}

	@Override
	public int countQuestion(String id) {
		
		return tripDAO.countQuestion(id);
	}

	@Override
	public List<QuestionDTO> selectMemberQuestion(String id) {
		List<QuestionDTO> articlesList = tripDAO.selectMemberQuestion(id);
		
		return articlesList;
	}

	@Override
	public List<QuestionDTO> selectAnswer() {
		List<QuestionDTO> answerList = tripDAO.selectAnswer();
		
		return answerList;
	}


	@Override
	public void insertNewQuestion(QuestionDTO questionDTO) {
		tripDAO.insertNewQuestion(questionDTO);
	}

	@Override
	public void insertReplyQuestion(QuestionDTO questionDTO) {
		tripDAO.insertReplyQuestion(questionDTO);
	}

	@Override
	public List<DormVO> getDormList(int dorm_category_no, Date start, Date end, int opt_wifi, int opt_parking, int opt_aircon, int opt_dryer, int opt_port, int room_person, int order, int price, String search) {
		List<DormVO> list = tripDAO.selectDormList(dorm_category_no, start, end, opt_wifi, opt_parking, opt_aircon, opt_dryer, opt_port, room_person, order, price, search);
		
		return list;
	}

	@Override
	public List<QuestionDTO> selectQuestion(int question_no) {
		List<QuestionDTO> QuestionList = tripDAO.selectQuestion(question_no);
		
		return QuestionList;
	}

	@Override
	public List<QuestionDTO> selectReply() {
		List<QuestionDTO> QuestionList = tripDAO.selectReply();
		
		return QuestionList;
	}

	@Override
	public void plusViewCount(int articleNo) {
		
	}

	@Override
	public void updateArticle(QuestionDTO questionDTO) {
		tripDAO.updateArticle(questionDTO);
	}

	@Override
	public List<QuestionDTO> selectmodQuestion(int question_no) {
		List<QuestionDTO> QuestionList = tripDAO.selectmodQuestion(question_no);
		
		return QuestionList;
	}

	@Override
	public void deleteArticle(int question_no) {
		tripDAO.deleteArticle(question_no);
	}
	
	@Override
	public List<DormDTO> selectMain_dormList() {
		List<DormDTO> list = new ArrayList();
		list = tripDAO.selectMain_dormList();
		return list;
	}
	@Override
	public List<QuestionDTO> selectAllQuestion(int parentno) {
		List<QuestionDTO> answerList = tripDAO.selectAllQuestion(parentno);		
		return answerList;
	}
	@Override
	public List<QuestionDTO> selectmodReply(int question_no) {
		List<QuestionDTO> reanswerList = tripDAO.selectmodReply(question_no);
		
		return reanswerList;
	}
	@Override
	public void updateReply(QuestionDTO questionDTO) {
		tripDAO.updateReply(questionDTO);	
	}
	
	@Override
	public void deleteReply(int question_no) {
		tripDAO.deleteReply(question_no);
	}
	@Override
	public void insertPayment(long pay_no, int pay_check, String member, String pay_ment, String pay_num, String real_name, String dorm_name, String room_name) {
		tripDAO.insertPayment(pay_no, pay_check, member, pay_ment, pay_num, real_name, dorm_name, room_name);
	}
	@Override
	public PaymentDTO selectPayment(long pay_no) {
		PaymentDTO dto = tripDAO.nopaynoSelect(pay_no);
		System.out.println("dto num " + dto.getPay_num());
		System.out.println("dto no " + dto.getPay_no());
		
		return dto;
		
	}
	@Override
	public long paynoSelect(int reserve_no) {
		long pay_no = tripDAO.paynoSelect(reserve_no);
		System.out.println("서비스에서 나온 pay_no값 : " + pay_no);
		return pay_no;
	}
	@Override
	public PaymentDTO nopaynoSelect(long pay_no) {
		PaymentDTO dto = tripDAO.nopaynoSelect(pay_no);
		System.out.println("서비스에서 나온 pay_no로 인한 dto결과값: " + dto );
		return dto;
	}
	@Override
	public int reviewChecking(int reserno) {
		return tripDAO.reviewChecking(reserno);
	}

	@Override
	public List<QuestionDTO> reselectReply(int question_no) {
		List<QuestionDTO> answerList = tripDAO.reselectReply(question_no);
		
		return answerList;
	}
	@Override
	public void reinsertReplyQuestion(QuestionDTO questionDTO) {
		tripDAO.reinsertReplyQuestion(questionDTO);
		
	}

}
