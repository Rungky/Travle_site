package com.spring.trip.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip.dao.TripDAO;
import com.spring.trip.dto.CheckDTO;
import com.spring.trip.dto.DormDTO;
import com.spring.trip.dto.DormVO;
import com.spring.trip.dto.MemberDTO;
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
		return rs;
	}

	@Override
	public CheckDTO checkList(int dorm_no, int room_no, String dorm_name, String room_name, Date reserve_checkin,
			Date reserve_checkout, int reserve_pay) {
		CheckDTO dto = tripDAO.checkList(dorm_no, room_no, dorm_name, room_name, reserve_checkin, reserve_checkout, reserve_pay);
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
			int room_no, int dorm_no) {
		tripDAO.insertReservation(member,reserve_checkin,reserve_checkout,reserve_pay,room_no,dorm_no);
	}

	@Override
	public MemberDTO memberDto(String member_id) {
		MemberDTO dto = tripDAO.memberDto(member_id);
		return dto;
	}

	@Override
	public int countQuestion(String id) {
		return 0;
	}

	@Override
	public List<QuestionDTO> selectMemberQuestion(String id) {
		List<QuestionDTO> articlesList = tripDAO.selectMemberQuestion(id);
		
		return articlesList;
	}

	@Override
	public List<QuestionDTO> selectAnswer() {
		List<QuestionDTO> answersList = tripDAO.selectAnswer();
		
		return answersList;
	}

	@Override
	public List<QuestionDTO> selectAllQuestion(int pageNum, int countPerPage) {
		return null;
	}

	@Override
	public void insertNewQuestion(QuestionDTO questionDTO) {
		tripDAO.insertNewQuestion(questionDTO);
	}

	@Override
	public void insertReplyQuestion(QuestionDTO questionDTO) {
		
	}

	@Override
	public List<DormVO> getDormList(int dorm_category_no, Date start, Date end, int opt_wifi, int opt_parking, int opt_aircon, int opt_dryer, int opt_port, int room_person, int order, int price, String search) {
		List<DormVO> list = tripDAO.selectDormList(dorm_category_no, start, end, opt_wifi, opt_parking, opt_aircon, opt_dryer, opt_port, room_person, order, price, search);
		
		return list;
	}

	@Override
	public List<QuestionDTO> selectQuestion(int question_no) {
		return null;
	}

	@Override
	public List<QuestionDTO> selectReply() {
		return null;
	}

	@Override
	public void plusViewCount(int articleNo) {
		
	}

	@Override
	public void updateArticle(QuestionDTO questionDTO) {
		
	}

	@Override
	public List<QuestionDTO> selectmodQuestion(int question_no) {
		return null;
	}

	@Override
	public void deleteArticle(int question_no) {
		
	}
	
	@Override
	public List<DormDTO> selectMain_dormList() {
		List<DormDTO> list = new ArrayList();
		list = tripDAO.selectMain_dormList();
		return list;
	}
}
