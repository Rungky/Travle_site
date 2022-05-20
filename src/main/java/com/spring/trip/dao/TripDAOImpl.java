package com.spring.trip.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.trip.dto.CheckDTO;
import com.spring.trip.dto.DormDTO;
import com.spring.trip.dto.DormVO;
import com.spring.trip.dto.LikeDTO;
import com.spring.trip.dto.MemberDTO;
import com.spring.trip.dto.QuestionDTO;
import com.spring.trip.dto.ReservationDTO;
import com.spring.trip.dto.ReviewDTO;
import com.spring.trip.dto.RoomDTO;

@Repository
public class TripDAOImpl implements TripDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public DormDTO selectDorm(int dormNo) {
		DormDTO dto = new DormDTO();
		dto = sqlSession.selectOne("mapper.trip.selectDorm", dormNo);
		DormDTO dtoTemp = scoreAverage(dormNo);
		dto.setReview_count(dtoTemp.getReview_count());
		dto.setScoreAvr(dtoTemp.getScoreAvr());
		return dto;
	}

	@Override
	public DormDTO scoreAverage(int dormNo) {
		DormDTO dto = new DormDTO();
		double scoreArv = 0;
		int count = 0;
		List<ReviewDTO> reviewdto = selectReviewsList(dormNo);
		try {
			for (int i = 0; i < reviewdto.size(); i++) {
				scoreArv += reviewdto.get(i).getReview_score();
				count++;
			}
			scoreArv = scoreArv / count;
			scoreArv = Math.round(scoreArv * 10) / 10.0;
			dto.setReview_count(count);
			dto.setScoreAvr(scoreArv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public void changeLike(int dormNo, int num) {
		int totalCount = selectDorm(dormNo).getLike_cnt();
		Map map = new HashMap();
		map.put("totalCount", totalCount + num);
		map.put("dormNo", dormNo);
		sqlSession.update("mapper.trip.changeLike", map);
		System.out.println("숙소 : "+selectDorm(dormNo).getDorm_name());
		System.out.println("조회수 : "+(totalCount+num));
	}

	@Override
	public boolean checkLike(int dormNo, String id) {
		int like = 0;
		boolean like_bl = false; 
		Map map = new HashMap();
		map.put("dormNo", dormNo);
		map.put("id", id);
		like = sqlSession.selectOne("mapper.trip.checkLike", map);
		if (like == 0) {
			like_bl = false;
			System.out.println("좋아요 체크 안한 숙소");
		} else {
			like_bl = true;
			System.out.println("좋아요 체크한 숙소");
		}
		return like_bl;
	}

	@Override
	public void insertLike(int dormNo, String id) {
		Map map = new HashMap();
		map.put("dormNo", dormNo);
		map.put("id", id);
		sqlSession.insert("mapper.trip.insertLike", map);
		System.out.println(id+"님"+" 좋아요한 숙소 : "+selectDorm(dormNo).getDorm_name());
	}

	@Override
	public void deleteLike(int dormNo, String id) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("dormNo", dormNo);
		sqlSession.delete("mapper.trip.deleteLike", map);
		System.out.println(id+"님"+" 좋아요 취소한 숙소 : "+selectDorm(dormNo).getDorm_name());
	}

	@Override
	public List<RoomDTO> selectRoomsList(int dormNo) {
		List<RoomDTO> list = new ArrayList<RoomDTO>();
		list = sqlSession.selectList("mapper.trip.selectRoomsList", dormNo);
		System.out.print("룸 리스트 : ");
		for(int i = 0; i<list.size();i++) {
			System.out.print(list.get(i).getRoom_name());
			if(i!=list.size()-1)
				System.out.print(",");
		}
		System.out.println();
		return list;
	}

	@Override
	public List<RoomDTO> reservedRoomsList(int dormNo, Date checkIn, Date checkOut) {
		List<RoomDTO> list = new ArrayList<RoomDTO>();
		Map map = new HashMap();
		map.put("checkIn", checkIn);
		map.put("checkOut", checkOut);
		map.put("dormNo", dormNo);
		list = sqlSession.selectList("mapper.trip.reservedRoomsList", map);
		return list;
	}

	@Override
	public List<ReviewDTO> selectReviewsList(int dormNo) {
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();
		list = sqlSession.selectList("mapper.trip.selectReviewsList", dormNo);
		return list;
	}
	
	@Override
	public ReservationDTO selectReservation(int reserveno) {
		ReservationDTO dto = sqlSession.selectOne("mapper.trip.selectReservation", reserveno);
		System.out.println("예약번호 : " + dto.getReserve_no());
		System.out.println("숙소 이름 : " +selectDorm(dto.getDorm_no()).getDorm_name());
		return dto;
	}

	@Override
	public List<ReservationDTO> selectReservationsList(String member) {
		List<ReservationDTO> list = new ArrayList<ReservationDTO>();
		list = sqlSession.selectList("mapper.reser.selectReservationsList",member);
		System.out.println("모든 숙소 조회 성공");
		return list;
	}

	@Override
	public int reserDelete(int reserve_no) {
		int rs = -1;
		rs = sqlSession.delete("mapper.trip.reserDelete", reserve_no);
		System.out.println("예약취소 성공");
		return rs;
	}

	@Override
	public CheckDTO checkList(int dorm_no, int room_no, String dorm_name, String room_name, Date reserve_checkin,
			Date reserve_checkout, int reserve_pay) {
		CheckDTO dto = new CheckDTO();
		dto.setDorm_no(dorm_no);
		dto.setRoom_no(room_no);
		dto.setDorm_name(dorm_name);
		dto.setRoom_name(room_name);
		dto.setReserve_checkin(reserve_checkin);
		dto.setReserve_checkout(reserve_checkout);
		dto.setReserve_pay(reserve_pay);
		
		return dto;
	}

	@Override
	public void insertReview(String title, String contents, double reviewScore, Date date, String picture,
			int reserveNo, String memberId) {
		Map map = new HashMap();
		map.put("title", title);
		map.put("contents", contents);
		map.put("reviewScore", reviewScore);
		map.put("date", date);
		map.put("picture", picture);
		map.put("reserveNo", reserveNo);
		map.put("memberId", memberId);
		sqlSession.insert("mapper.trip.insertReview", map);
		System.out.println("제목 : "+ title);
		System.out.println("내용 : "+ contents);
		System.out.println("리뷰점수 : "+ reviewScore);
		System.out.println("날짜 : "+ date);
		System.out.println("사진 : "+ picture);
		System.out.println("예약번호 : "+ reserveNo);
		System.out.println("아이디 : "+ memberId);
	}

	@Override
	public int selectTotalQuestion() {
		int total = 0;
		return total;
	}

	@Override
	public void insertReservation(String member, Date reserve_checkin, Date reserve_checkout, int reserve_pay,
			int room_no, int dorm_no) {
		Map map = new HashMap();
		map.put("member", member);
		map.put("dorm_no", dorm_no);
		map.put("room_no", room_no);
		map.put("reserve_checkin", reserve_checkin);
		map.put("reserve_checkout", reserve_checkout);
		map.put("reserve_pay", reserve_pay);
		sqlSession.insert("mapper.reser.insertReservation", map);
		System.out.println("예약인서트 성공");
	}

	@Override
	public MemberDTO memberDto(String member_id) {
		MemberDTO dto = new MemberDTO();
		dto = sqlSession.selectOne("mapper.reser.memberDto",member_id );
		return dto;
	}

	@Override
	public int countQuestion(String id) {
		int count = 0 ;
		return count;
	}

	@Override
	public List<QuestionDTO> selectMemberQuestion(String id) {
		List<QuestionDTO> QuestionList = new ArrayList();
		Map map = new HashMap();
		map.put("id",id);
		QuestionList  = sqlSession.selectList("mapper.qna.selectMemberQuestion", map);
		return QuestionList;
	}

	@Override
	public List<QuestionDTO> selectAnswer() {
		List<QuestionDTO> answerList = new ArrayList();
		
		answerList=sqlSession.selectList("mapper.qna.selectAnswer");
		return answerList;
	}

	@Override
	public List<QuestionDTO> selectAllQuestion(int pageNum, int countPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertNewQuestion(QuestionDTO questionDTO) {
		
	sqlSession.insert("mapper.qna.insertNewQuestion", questionDTO);
		
	}

	@Override
	public void insertReplyQuestion(QuestionDTO questionDTO) {

		sqlSession.insert("mapper.qna.insertReplyQuestion", questionDTO);
	}

	@Override
	public List<DormVO> selectDormList(int dorm_category_no, Date start, Date end, int opt_wifi, int opt_parking, int opt_aircon, int opt_dryer, int opt_port, int room_person, int order, int price, String search){
		Map map = new HashMap();

		
		map.put("dorm_category_no", dorm_category_no);
		map.put("date_s", start);
		map.put("date_e", end);
		map.put("opt_wifi", opt_wifi);
		map.put("opt_parking", opt_parking);
		map.put("opt_aircon", opt_aircon);
		map.put("opt_dryer", opt_dryer);
		map.put("opt_port", opt_port);
		map.put("room_person", room_person);
		map.put("order", order);
		map.put("price", price);
		map.put("search", search);
		
		List<DormVO> dormList = sqlSession.selectList("mapper.trip.selectDormList", map);

		return dormList;
	}

	@Override
	public List<QuestionDTO> selectQuestion(int question_no) {
		List<QuestionDTO> QuestionList = new ArrayList();
		QuestionList =  sqlSession.selectList("mapper.qna.selectQuestion", question_no);
		
		return QuestionList;
	}

	@Override
	public List<QuestionDTO> selectReply() {
		List<QuestionDTO> QuestionList = new ArrayList();
		QuestionList =  sqlSession.selectList("mapper.qna.selectReply");
		
		return QuestionList;
	}

	@Override
	public void plusViewCount(int articleNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateArticle(QuestionDTO questionDTO) {
		
		sqlSession.update("mapper.qna.updateArticle", questionDTO);
	}

	@Override
	public List<QuestionDTO> selectmodQuestion(int question_no) {
		List<QuestionDTO> QuestionList = new ArrayList();
		QuestionList =  sqlSession.selectList("mapper.qna.selectmodQuestion", question_no);
		
		return QuestionList;
	}

	@Override
	public void deleteArticle(int question_no) {
		
		sqlSession.delete("mapper.qna.deleteArticle", question_no);
	}

	@Override
	public List<DormDTO> selectMain_dormList() {
		List<DormDTO> list= sqlSession.selectList("mapper.trip.selectMain_dormList");
		return list ;
	}

	
}
