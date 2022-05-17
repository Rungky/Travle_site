package com.spring.trip.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.trip.dao.MemberDAOImpl;
import com.spring.trip.dao.TripDAOImpl;
import com.spring.trip.dto.CheckDTO;
import com.spring.trip.dto.DormDTO;
import com.spring.trip.dto.DormVO;
import com.spring.trip.dto.MemberDTO;
import com.spring.trip.dto.QuestionDTO;
import com.spring.trip.dto.ReservationDTO;
import com.spring.trip.dto.ReviewDTO;
import com.spring.trip.dto.RoomDTO;
import com.spring.trip.service.MemberServiceImpl;
import com.spring.trip.service.QnaServiceImpl;
import com.spring.trip.service.TripService;


@Controller
public class TripController extends MultiActionController{
	
	@Autowired
	private TripService tripService;
	
	HttpSession session;
	
	@RequestMapping(value="/trip/detail.do", method=RequestMethod.GET)
	public ModelAndView detail(
			@RequestParam("dormno") int dormno,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		session = request.getSession();
		ModelAndView mav= new ModelAndView();
		String id = "n";
		if (session.getAttribute("id") != null) {
			id = (String) session.getAttribute("id");
		}
		session.setAttribute("id", "jin5856u"); // 임의 값
		Calendar cal = Calendar.getInstance();
		String format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		cal.add(cal.DATE, +1);
		String tomorrow = sdf.format(cal.getTime());
		
		java.util.Date today = new java.util.Date();
		String checkin = sdf.format(today);
		String checkout = tomorrow;
		
		if(request.getParameter("reserve_checkin") != null)
			checkin= request.getParameter("reserve_checkin");
		if(request.getParameter("reserve_checkout") != null)
			checkout = request.getParameter("reserve_checkout");
		
		java.util.Date checkindate = sdf.parse(checkin);
		java.util.Date checkoutdate = sdf.parse(checkout);
		
		if(checkindate.after(checkoutdate) || checkin.equals(checkout)) {
			checkin = sdf.format(today);
			checkout = tomorrow;
			checkindate = sdf.parse(checkin);
			checkoutdate = sdf.parse(checkout);
			mav.addObject("dateerror", "error");
		}
		
		long reserveday = (checkoutdate.getTime() - checkindate.getTime()) / (24*60*60*1000);
		DormDTO dormdto = tripService.selectDorm(dormno);
		Date checkIn = new Date(checkindate.getTime());
		Date checkOut = new Date(checkoutdate.getTime());
		List<RoomDTO> roomsList = tripService.selectRoomsList(dormno);
		List<RoomDTO> reservedroomsList = tripService.reservedRoomsList(dormno, checkIn, checkOut);
		boolean like_tg = tripService.checkLike(dormno, id);
		for(int i = 0 ; i <roomsList.size();i++) {
			for(int j = 0; j <reservedroomsList.size();j++) {
				if (roomsList.get(i).getRoom_no()==reservedroomsList.get(j).getRoom_no()) {
					roomsList.get(i).setReserved(1);
					break;
				} else {
					roomsList.get(i).setReserved(0);
				}
			} 
		}
		
		List<ReviewDTO> reviewsList = tripService.selectReviewsList(dormno);
		mav.addObject("dormdto",dormdto);
		mav.addObject("roomsList",roomsList);
		mav.addObject("reviewsList",reviewsList);
		mav.addObject("roomday",reserveday);
		mav.addObject("tomorrow",tomorrow);
		mav.addObject("checkin",checkin);
		mav.addObject("checkout",checkout);
		mav.addObject("like_tg",like_tg);
		mav.setViewName("detail");
		System.out.println("체크인 : "+checkin+" ~ 체크아웃 : "+checkout);
		System.out.println("detail 페이지");
		return mav;
	}
	
	@RequestMapping(value="/trip/upload.do", method=RequestMethod.POST)
	public ModelAndView fileupload(
			RedirectAttributes redirect,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
	ModelAndView mav= new ModelAndView();
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
	
	//경로 수정
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
					System.out.println("fileName = "+fileName);
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
		System.out.println("텍스트 null오류");
		mav.setViewName("redirect:review.do?reserve_no="+reservNo+"");
	} else {
		System.out.println("INSERT");
		tripService.insertReview(title, contents, score, date, picture, reservNo, memberId);
		redirect.addAttribute("dormno",dormno);
		mav.setViewName("redirect:detail.do");
	}
		return mav;
	}
	
	@RequestMapping(value="/trip/review.do", method=RequestMethod.GET)
	public ModelAndView review(
			@RequestParam("reserve_no") int reserveno,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		ModelAndView mav= new ModelAndView();
		session = request.getSession();
		session.setAttribute("id", "jin5856u"); // 임의 값
		ReservationDTO reservationdto = tripService.selectReservation(reserveno);
		mav.addObject("reserveno",reserveno);
		mav.addObject("reservationdto",reservationdto);
		mav.setViewName("review");
		return mav;
	}
	
	@RequestMapping(value="/trip/like.do", method=RequestMethod.GET)
	public void like(
			@RequestParam("dormno") int dormno,
			@RequestParam("like") boolean like_tg,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		session = request.getSession();
		String id = (String) session.getAttribute("id");
		if(like_tg) {
			tripService.deleteLike(dormno,id);
			tripService.changeLike(dormno, -1);
		} else {
			tripService.insertLike(dormno,id);
			tripService.changeLike(dormno, 1);
		}
		like_tg = !like_tg;
		PrintWriter out = response.getWriter();
		out.print("{\"param\":\""+like_tg+"\"}");
		return;
		
	}
	

}
