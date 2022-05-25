package com.spring.trip.controller;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.trip.dto.CheckDTO;
import com.spring.trip.dto.DormDTO;
import com.spring.trip.dto.DormVO;
import com.spring.trip.dto.MemberDTO;
import com.spring.trip.dto.PaymentDTO;
import com.spring.trip.dto.QuestionDTO;
import com.spring.trip.dto.ReservationDTO;
import com.spring.trip.dto.ReviewDTO;
import com.spring.trip.dto.RoomDTO;
import com.spring.trip.service.TripService;

import net.sf.json.JSONObject;

@Controller
public class TripController extends MultiActionController {

	@Autowired
	private TripService tripService;

	HttpSession session;

	@RequestMapping(value = "/trip/detail.do", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam("dormno") int dormno, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		session = request.getSession();
		ModelAndView mav = new ModelAndView();
		String id = "n";
		if (session.getAttribute("id") != null) {
			id = (String) session.getAttribute("id");
		}
		Calendar cal = Calendar.getInstance();
		String format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		cal.add(cal.DATE, +1);
		String tomorrow = sdf.format(cal.getTime());

		java.util.Date today = new java.util.Date();
		String checkin = sdf.format(today);
		String today_str = checkin;
		String checkout = tomorrow;

		if (request.getParameter("reserve_checkin") != null)
			checkin = request.getParameter("reserve_checkin");
		if (request.getParameter("reserve_checkout") != null)
			checkout = request.getParameter("reserve_checkout");

		java.util.Date checkindate = sdf.parse(checkin);
		java.util.Date checkoutdate = sdf.parse(checkout);

		if (checkindate.after(checkoutdate) || checkin.equals(checkout)) {
			checkin = sdf.format(today);
			checkout = tomorrow;
			checkindate = sdf.parse(checkin);
			checkoutdate = sdf.parse(checkout);
			mav.addObject("dateerror", "error");
		}

		long reserveday = (checkoutdate.getTime() - checkindate.getTime()) / (24 * 60 * 60 * 1000);
		DormDTO dormdto = tripService.selectDorm(dormno);
		Date checkIn = new Date(checkindate.getTime());
		Date checkOut = new Date(checkoutdate.getTime());
		List<RoomDTO> roomsList = tripService.selectRoomsList(dormno);
		List<RoomDTO> reservedroomsList = tripService.reservedRoomsList(dormno, checkIn, checkOut);
		boolean like_tg = tripService.checkLike(dormno, id);
		for (int i = 0; i < roomsList.size(); i++) {
			for (int j = 0; j < reservedroomsList.size(); j++) {
				if (roomsList.get(i).getRoom_no() == reservedroomsList.get(j).getRoom_no()) {
					roomsList.get(i).setReserved(1);
					break;
				} else {
					roomsList.get(i).setReserved(0);
				}
			}
		}

		List<ReviewDTO> reviewsList = tripService.selectReviewsList(dormno);
		mav.addObject("dormdto", dormdto);
		mav.addObject("roomsList", roomsList);
		mav.addObject("reviewsList", reviewsList);
		mav.addObject("roomday", reserveday);
		mav.addObject("today", today_str);
		mav.addObject("tomorrow", tomorrow);
		mav.addObject("reserve_checkin", checkin);
		mav.addObject("reserve_checkout", checkout);
		mav.addObject("like_tg", like_tg);
		mav.setViewName("detail");
		return mav;
	}

	@RequestMapping(value = "/trip/upload.do", method = RequestMethod.POST)
	public ModelAndView fileupload(RedirectAttributes redirect, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		session = request.getSession();
		String title = "";
		String contents = "";
		double score = 0;
		long now = System.currentTimeMillis();
		Date date = new Date(now);
		int reservNo = 0;
		String memberId = "";
		int dormno = 0;

		String picture = "none";
		String encoding = "utf-8";

		// 寃쎈줈 �닔�젙
		File currentDirPath = new File("C:\\workstation\\a_final\\src\\main\\webapp\\resources\\review");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);

		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));

					if (fileItem.getFieldName().equals("reviewtitle")) {

						title = fileItem.getString(encoding);
					}
					if (fileItem.getFieldName().equals("reviewcontents")) {
						contents = fileItem.getString(encoding);
					}
					if (fileItem.getFieldName().equals("reviewscore")) {
						score = Double.parseDouble(fileItem.getString(encoding));
					}
					if (fileItem.getFieldName().equals("reserve_no")) {
						reservNo = Integer.parseInt(fileItem.getString(encoding));
					}
					if (fileItem.getFieldName().equals("memberid")) {
						memberId = fileItem.getString(encoding);
					}
					if (fileItem.getFieldName().equals("dormno")) {
						dormno = Integer.parseInt(fileItem.getString(encoding));
					}
				} else {
					System.out.println("param:" + fileItem.getFieldName());
					System.out.println("file name:" + fileItem.getName());
					System.out.println("file size:" + fileItem.getSize() + "bytes");

					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx + 1);
						long timestamp = System.currentTimeMillis();
						String temp = "";
						temp = temp + timestamp;
						temp = temp.substring(1, temp.length());
						fileName = temp + "_" + fileName;
						picture = fileName;
						System.out.println("fileName = " + fileName);
						File uploadFile = new File(currentDirPath + "\\" + fileName);
						fileItem.write(uploadFile);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		memberId = (String) session.getAttribute("id");

		if (title.equals("") || contents.equals("")) {
			mav.addObject("textnull", "textnull");
			System.out.println("�뀓�뒪�듃 null�삤瑜�");
			mav.setViewName("redirect:review.do?reserve_no=" + reservNo + "");
		} else {
			System.out.println("INSERT");
			tripService.insertReview(title, contents, score, date, picture, reservNo, memberId);
			Calendar cal = Calendar.getInstance();
			String format = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			cal.add(cal.DATE, +1);
			String tomorrow = sdf.format(cal.getTime());

			java.util.Date today = new java.util.Date();
			String checkin = sdf.format(today);
			String checkout = tomorrow;
			redirect.addAttribute("dormno", dormno);
			redirect.addAttribute("reserve_checkout", checkout);
			redirect.addAttribute("reserve_checkin", checkin);
			mav.setViewName("redirect:detail.do");
		}
		return mav;
	}

	@RequestMapping(value = "/trip/review.do", method = RequestMethod.GET)
	public ModelAndView review(@RequestParam("reserve_no") int reserveno, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		session = request.getSession();
		ReservationDTO reservationdto = tripService.selectReservation(reserveno);
		mav.addObject("reserveno", reserveno);
		mav.addObject("reservationdto", reservationdto);
		mav.setViewName("review");
		return mav;
	}

	@RequestMapping(value = "/trip/like.do", method = RequestMethod.GET)
	public void like(@RequestParam("dormno") int dormno, @RequestParam("like") boolean like_tg,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		session = request.getSession();
		String id = (String) session.getAttribute("id");
		if (like_tg) {
			tripService.deleteLike(dormno, id);
			tripService.changeLike(dormno, -1);
		} else {
			tripService.insertLike(dormno, id);
			tripService.changeLike(dormno, 1);
		}
		like_tg = !like_tg;
		PrintWriter out = response.getWriter();
		out.print("{\"param\":\"" + like_tg + "\"}");
		return;

	}

	@RequestMapping(value = "/trip/reservation.do", method = { RequestMethod.GET })
	public ModelAndView reservation(@RequestParam(defaultValue = "0") int dorm_category_no,
			@RequestParam(required = false) Date date_e, @RequestParam(required = false) Date date_s,
			@RequestParam(defaultValue = "0") int opt_wifi, @RequestParam(defaultValue = "0") int opt_parking,
			@RequestParam(defaultValue = "0") int opt_aircon, @RequestParam(defaultValue = "0") int opt_dryer,
			@RequestParam(defaultValue = "0") int opt_port, @RequestParam(defaultValue = "1") int room_person,
			@RequestParam(defaultValue = "0") int order, @RequestParam(defaultValue = "5") int price,
			@RequestParam(defaultValue = "") String search, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = newDtFormat.format(cal.getTime());

		cal.add(cal.DATE, +1);
		String date2 = newDtFormat.format(cal.getTime());
		Date end;
		Date start;
		Date today;
		today = new Date(newDtFormat.parse(date1).getTime());
		if (date_e == null) {
			end = new Date(newDtFormat.parse(date2).getTime());
		} else {
			end = date_e;
		}
		if (date_s == null) {
			start = new Date(newDtFormat.parse(date1).getTime());
		} else {
			start = date_s;
		}
		List<DormVO> dormsList = tripService.getDormList(dorm_category_no, start, end, opt_wifi, opt_parking,
				opt_aircon, opt_dryer, opt_port, room_person, order, price, search);

		ModelAndView mav = new ModelAndView();
		mav.addObject("date_s", start);
		mav.addObject("date_e", end);
		mav.addObject("today", today);
		mav.addObject("dorm_category_no", dorm_category_no);
		mav.addObject("opt_wifi", opt_wifi);
		mav.addObject("opt_parking", opt_parking);
		mav.addObject("opt_aircon", opt_aircon);
		mav.addObject("opt_dryer", opt_dryer);
		mav.addObject("opt_port", opt_port);
		mav.addObject("room_person", room_person);
		mav.addObject("order", order);
		mav.addObject("price", price);
		mav.addObject("search", search);

		mav.addObject("dormsList", dormsList);

		mav.setViewName("reservation");

		return mav;
	}

	@RequestMapping(value = "/trip/result.do", method = RequestMethod.GET)
	public ModelAndView result(
			@RequestParam("dorm_no") int dorm_no, 
			@RequestParam("room_no") int room_no,
			@RequestParam("dorm_name") String dorm_name,
			@RequestParam("room_name") String room_name,
			@RequestParam("reserve_checkin") Date reserve_checkin,
			@RequestParam("reserve_checkout") Date reserve_checkout, 
			@RequestParam("reserve_pay") int reserve_pay,
			@RequestParam("pay_check") int pay_check,
			@RequestParam("pay_ment") String pay_ment,
			@RequestParam("pay_num") String pay_num,
			@RequestParam("real_name") String real_name,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		System.out.println(dorm_no);
		System.out.println(reserve_checkout);
		System.out.println(pay_ment);
		System.out.println(real_name);
		System.out.println(pay_num);
		
		
		HttpSession session = request.getSession();
		String member = (String) session.getAttribute("id");
		long pay_no = System.currentTimeMillis();
		tripService.insertPayment(pay_no, pay_check, member, pay_ment, pay_num, real_name, dorm_name, room_name);
		PaymentDTO dto = tripService.selectPayment(pay_no);
		System.out.println("컨트롤러 pay_no" + pay_no);
	
		tripService.insertReservation(member, reserve_checkin, reserve_checkout, reserve_pay, room_no, dorm_no, pay_no, pay_check);
		
		System.out.println("결제 인서트 성공");
		mav.addObject("member_id", member);
		mav.setViewName("forward:/trip/history.do");

		return mav;
	}

	//예약취소하기
	@RequestMapping(value = "/trip/reserDelete.do", method = RequestMethod.GET)
	public ModelAndView reserDelete(@RequestParam("reserve_no") int reserve_no,
			@RequestParam("reserve_checkin") Date reserve_checkin, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		System.out.println("reserDelete.do");
		long miliseconds = System.currentTimeMillis();
		Date date = new Date(miliseconds);
		if (reserve_checkin.after(date))
			tripService.reserDelete(reserve_no);
		mav.setViewName("forward:/trip/history.do");
		return mav;
	}

	//예약내역 삭제하기
	@RequestMapping(value = "/trip/Delete.do", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject Delete(@RequestParam("reserve_no") int reserve_no, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject resMap = new JSONObject();
		try {
			System.out.println("delete.do 진입함");
			int result = tripService.reserDelete(reserve_no);
			System.out.println(result);
			resMap.put("msg", result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	
	@RequestMapping(value = "/trip/bridge.do", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject bridge(
			@RequestParam("reserve_no") int reserve_no, 
			HttpServletRequest request,			HttpServletResponse response) {
		JSONObject resMap = new JSONObject();
		PaymentDTO dto = new PaymentDTO();
		try {
			System.out.println("bridge.do 진입함");
			
			System.out.println(tripService.paynoSelect(reserve_no));
			long pay_no = tripService.paynoSelect(reserve_no);
			System.out.println("브릿지 pay_no" + pay_no);
			System.out.println(pay_no);
			dto = tripService.selectPayment(pay_no);
			String real_name= dto.getReal_name();
			String pay_ment = dto.getPay_ment();
			String pay_num = dto.getPay_num();
			String dorm_name = dto.getDorm_name();
			String room_name = dto.getRoom_name();
			int pay_check = dto.getPay_check();
			resMap.put("real_name", real_name);
			resMap.put("pay_ment", pay_ment);
			resMap.put("pay_num", pay_num);
			resMap.put("dorm_name", dorm_name);
			resMap.put("room_name", room_name);
			resMap.put("pay_check", pay_check);
			
			System.out.println("resMAp에 담아준 값은: " + real_name);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	@RequestMapping(value = "/trip/history.do", method = RequestMethod.GET)
	public ModelAndView history(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("history.do 진입");
		HttpSession session = request.getSession();
		String member = (String) session.getAttribute("id");
		System.out.println(member);
		ModelAndView mav = new ModelAndView();
		try {
			MemberDTO dto = tripService.memberDto(member);
			mav.addObject("dto", dto);
			List<ReservationDTO> reserList = tripService.selectReservationsList(member);
			mav.addObject("reserList", reserList);
			for(int i=0;i<reserList.size();i++) {
				int review_checking = tripService.reviewChecking(reserList.get(i).getReserve_no());
				reserList.get(i).setReser_review(review_checking);
			}
			if (reserList != null && reserList.size() > 0) {
				System.out.println("List예약내역 출력시작");
				mav.setViewName("history");

			} else if (reserList.size() == 0 && member != null) {
				System.out.println("예약내역 없음");
				mav.setViewName("nohistory");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/trip/page8.do", method = RequestMethod.GET)
	public ModelAndView page8(@RequestParam("dormno") int dorm_no, @RequestParam("roomno") int room_no,
			@RequestParam("dormname") String dorm_name, @RequestParam("roomname") String room_name,
			@RequestParam("reserve_pay") int roompay, @RequestParam("reserve_checkin") Date reserve_checkin,
			@RequestParam("reserve_checkout") Date reserve_checkout, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		String member = (String) session.getAttribute("id");
		ModelAndView mav = new ModelAndView();
		try {
			MemberDTO dto = tripService.memberDto(member);
			mav.addObject("dto", dto);
			CheckDTO checkDto = tripService.checkList(dorm_no, room_no, dorm_name, room_name, reserve_checkin,
					reserve_checkout, roompay);
			mav.addObject("check", checkDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("page8");
		return mav;
	}

	@RequestMapping(value = { "trip/main.do", "trip/", "trip", "trip/main" }, method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {

		Calendar cal = Calendar.getInstance();
		String format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar calendar = new GregorianCalendar();
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
		
		String reserve_checkin = SDF.format(calendar.getTime());		
		calendar.add(Calendar.DATE, +1);	
		String reserve_checkout = SDF.format(calendar.getTime());		
		
		List<DormDTO> dormList = tripService.selectMain_dormList();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		mav.addObject("dormList", dormList);
		mav.addObject("reserve_checkin", reserve_checkin);
		mav.addObject("reserve_checkout", reserve_checkout);
		return mav;
	}
	
	//문의게시판 조회
	@RequestMapping(value = "/trip/qna.do", method = RequestMethod.GET)
	public ModelAndView qna(
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
	session = request.getSession();
	ModelAndView mav= new ModelAndView();
	String id = "";
		if(session.getAttribute("id")!=null) {
			// 로그인 했으면 아이디 받아오기
			id = (String) session.getAttribute("id");
		}
		if("".equals(id)) { // 로그인 안하면 로그인 페이지로
			mav.setViewName("redirect:login.do"); // 로그인 페이지로
//			return; // 메소드 종료
		}else {
			
			List<QuestionDTO> questionList = tripService.selectMemberQuestion(id);
			List<QuestionDTO> answerList = tripService.selectAnswer();
			
			
			int nowPage = 1; // 기본 값
			if(request.getParameter("nowPage")!=null) // 지금 페이지가 어딘지 값 받기
				nowPage = Integer.parseInt(request.getParameter("nowPage"));
//		System.out.println("nowPage : " + nowPage);
			int total = tripService.countQuestion(id);  // 게시물 수 부모 없는글 카운트
//		System.out.println("total : " + total);
			int pageNum = 5; // 한 페이지 게시물 5 개씩 (임의로 정함)
			int pagingNum = 5; // 페이징 5개씩
			int totalPage = (int) Math.ceil((double)total / pageNum); // 총 페이지 수
//		System.out.println("totalPage : " + totalPage);
			int totalPageCount = (totalPage+4) / pagingNum; // 페이징 수
//		System.out.println("totalPageCount : " + totalPageCount);
			int nowPageCount = (nowPage+4) / pagingNum; // 지금 페이징
//		System.out.println("nowPageCount : " + nowPageCount);
			int beginPage = 1 + (pageNum * (nowPage-1)); // 해당 페이지 게시물 begin 
//		System.out.println("beginPage : " + beginPage);
			int endPage = pageNum;	
			if (totalPage == nowPage) {
				endPage = total; 	// 마지막 페이지 일경우 게시물 범위 끝까지
			} else {
				endPage = pageNum + (pageNum * (nowPage - 1));	// 해당 페이지 게시물 end
			}
			mav.addObject("questionList", questionList); // 부모 없는글
			mav.addObject("answerList", answerList);
			mav.addObject("nowPageCount", nowPageCount); // 지금 페이징
			mav.addObject("totalPageCount", totalPageCount); // 총 페이징
			mav.addObject("totalPage", totalPage); // 마지막 페이지
			mav.addObject("beginPage", beginPage); // 해당 페이지 게시물 begin
			mav.addObject("endPage", endPage); // 해당 페이지 게시물 end
			mav.addObject("nowPage", nowPage); // 지금 페이지
			mav.setViewName("qna");
		}
		return mav;
	}

	//문의작성페이지
	@RequestMapping(value = "/trip/qnaForm.do", method = RequestMethod.GET)
	public String questionWriteForm() {
		return "questionWrite";
	}
	
	//문의글쓰기
	@RequestMapping(value = "/trip/addqna.do", method = RequestMethod.GET)
	public ModelAndView questionWrite(
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

	ModelAndView mav= new ModelAndView();
	
		String id= (String)session.getAttribute("id");
		
		QuestionDTO qdto = new QuestionDTO();
		qdto.setQuestion_title(title);
		qdto.setQuestion_contents(content);
		qdto.setQuestion_parentno(0);
		
		
		long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
		qdto.setQuestion_date(date);
		qdto.setQuestion_picture("sss");
		qdto.setQuestion_view(0);
		qdto.setMember_id(id);
		
		tripService.insertNewQuestion(qdto);

		
		mav.setViewName("redirect:qna.do");
		return mav;
	
	}
	
	//답변작성페이지
	@RequestMapping(value = "/trip/answerqna.do", method = RequestMethod.GET)
	public ModelAndView qna_answer(
			@RequestParam("product_no") int product_no,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

	ModelAndView mav= new ModelAndView();

	List<QuestionDTO> QuestionList = new ArrayList<QuestionDTO>();
	
	QuestionList= tripService.selectQuestion(product_no);

	mav.addObject("questionList", QuestionList);
	mav.setViewName("qna_answer");
	return mav;
	
	}
	
	//답변작성
	@RequestMapping(value = "/trip/replyqna.do", method = RequestMethod.GET)
	public ModelAndView qna_answer(
			@RequestParam("recontent") String recontent,
			@RequestParam("parentNO") int parentNO,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

	ModelAndView mav= new ModelAndView();
	
		String id= (String)session.getAttribute("id");
		
		QuestionDTO qdto = new QuestionDTO();
		qdto.setQuestion_contents(recontent);
		qdto.setQuestion_parentno(parentNO);
		qdto.setQuestion_title("[답변]");
		
		long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
		qdto.setQuestion_date(date);
		qdto.setQuestion_picture("picture");
		qdto.setQuestion_view(0);
		qdto.setMember_id(id);
		
		tripService.insertReplyQuestion(qdto);
		mav.setViewName("redirect:replylist.do");
		return mav;
	
	}
	
	//팝업창 닫기
	@RequestMapping(value = "/trip/replylist.do", method = RequestMethod.GET)
	public ModelAndView replylist(
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

	ModelAndView mav= new ModelAndView();

	mav.setViewName("close");
	return mav;
	
	}
	
	//수정페이지
	@RequestMapping(value = "/trip/modwrite.do", method = RequestMethod.GET)
	public ModelAndView qna_modpage(
			@RequestParam("select_no") int select_no,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

	ModelAndView mav= new ModelAndView();

	List<QuestionDTO> QuestionList = new ArrayList<QuestionDTO>();
	
	QuestionList= tripService.selectQuestion(select_no);

	mav.addObject("questionList", QuestionList);
	mav.setViewName("modquestionWrite");
	return mav;
	
	}
	
	//수정하기
	@RequestMapping(value = "/trip/modqna.do", method = RequestMethod.GET)
	public ModelAndView qna_mod(
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("questionNO") int questionNO,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

	ModelAndView mav= new ModelAndView();

	//List<QuestionDTO> QuestionList = new ArrayList<QuestionDTO>();
	
	QuestionDTO qdto = new QuestionDTO();
	qdto.setQuestion_no(questionNO);
	qdto.setQuestion_title(title);
	qdto.setQuestion_contents(content);
	
	tripService.updateArticle(qdto);

	//mav.addObject("questionList", QuestionList);
	mav.setViewName("close");
	return mav;
	
	}
	
	//삭제하기
	@RequestMapping(value = "/trip/removeqna.do", method = RequestMethod.GET)
	public ModelAndView qna_remove(
			@RequestParam("remove_no") int remove_no,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		ModelAndView mav= new ModelAndView();
	
		tripService.deleteArticle(remove_no);
	
		mav.setViewName("redirect:qna.do");
		return mav;
	}
	
	//답글수정페이지
	@RequestMapping(value = "/trip/modreplywrite.do", method = RequestMethod.GET)
	public ModelAndView qna_modreplypage(
			@RequestParam("reply_no") int reply_no,
			@RequestParam("parent_no") int parent_no,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

	ModelAndView mav= new ModelAndView();

	List<QuestionDTO> QuestionList = new ArrayList<QuestionDTO>();
	List<QuestionDTO> answerList = new ArrayList<QuestionDTO>();
	
	answerList= tripService.selectmodReply(reply_no);
	QuestionList= tripService.selectAllQuestion(parent_no);

	mav.addObject("questionList", QuestionList);
	mav.addObject("answerList", answerList);
	mav.setViewName("qna_modanswer");
	return mav;
	
	}
	
	//답글수정
	@RequestMapping(value = "/trip/modreply.do", method = RequestMethod.GET)
	public ModelAndView qna_modreply(
			@RequestParam("recontent") String recontent,
			@RequestParam("ReplyNO") int ReplyNO,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

	ModelAndView mav= new ModelAndView();

	List<QuestionDTO> QuestionList = new ArrayList<QuestionDTO>();
	
	QuestionDTO qdto = new QuestionDTO();
	qdto.setQuestion_no(ReplyNO);
	qdto.setQuestion_contents(recontent);
	
	tripService.updateReply(qdto);

	mav.addObject("questionList", QuestionList);
	mav.setViewName("close");
	return mav;
	
	}
	
	//답글삭제
	@RequestMapping(value = "/trip/removereply.do", method = RequestMethod.GET)
	public ModelAndView qna_removereply(
			@RequestParam("removereply_no") int removereply_no,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

	ModelAndView mav= new ModelAndView();
	
	tripService.deleteReply(removereply_no);
	
	mav.setViewName("redirect:qna.do");
	return mav;
	}
}
