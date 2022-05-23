package com.spring.trip.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.trip.dto.DormDTO;
import com.spring.trip.dto.MemberDTO;
import com.spring.trip.dto.QuestionDTO;
import com.spring.trip.service.AdminService;
import com.spring.trip.service.MemberService;
import com.spring.trip.service.TripService;

@Controller
public class AdminController extends MultiActionController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private MemberService memberService;
	
	
	HttpSession session;
	
	@RequestMapping(value="/trip/admin.do", method=RequestMethod.GET)
	public ModelAndView admin(
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		session = request.getSession();
		String member_id = (String) session.getAttribute("id");
		
		MemberDTO memberDTO =memberService.select_myMember(member_id);
		
		if(!(memberDTO.getMember_authority().equals("admin"))) {
			ModelAndView mav= new ModelAndView("redirect:/trip/main.do");
			return mav;
		}
		
		ModelAndView mav= new ModelAndView();
		mav.addObject("memberDTO",memberDTO);
		List <MemberDTO> membersList = adminService.allMembers();
		mav.addObject("membersList", membersList);
		List<DormDTO> dormslist = adminService.allDormsList();
		mav.addObject("dormsList", dormslist);
		List<QuestionDTO> questionList = adminService.allQuestion();
		mav.addObject("questionList", questionList);
		
		int nowPage = 1; // 기본 값
		if(request.getParameter("nowPage")!=null) // 지금 페이지가 어딘지 값 받기
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
//	System.out.println("nowPage : " + nowPage);
		int total = adminService.countQuestion();  // 게시물 수 부모 없는글 카운트
//	System.out.println("total : " + total);
		int pageNum = 5; // 한 페이지 게시물 5 개씩 (임의로 정함)
		int pagingNum = 5; // 페이징 5개씩
		int totalPage = (int) Math.ceil((double)total / pageNum); // 총 페이지 수
//	System.out.println("totalPage : " + totalPage);
		int totalPageCount = (totalPage+4) / pagingNum; // 페이징 수
//	System.out.println("totalPageCount : " + totalPageCount);
		int nowPageCount = (nowPage+4) / pagingNum; // 지금 페이징
//	System.out.println("nowPageCount : " + nowPageCount);
		int beginPage = 1 + (pageNum * (nowPage-1)); // 해당 페이지 게시물 begin 
//	System.out.println("beginPage : " + beginPage);
		int endPage = pageNum;	
		if (totalPage == nowPage) {
			endPage = total; 	// 마지막 페이지 일경우 게시물 범위 끝까지
		} else {
			endPage = pageNum + (pageNum * (nowPage - 1));	// 해당 페이지 게시물 end
		}
		
		
		
		mav.addObject("nowPageCount", nowPageCount); // 지금 페이징
		mav.addObject("totalPageCount", totalPageCount); // 총 페이징
		mav.addObject("totalPage", totalPage); // 마지막 페이지
		mav.addObject("beginPage", beginPage); // 해당 페이지 게시물 begin
		mav.addObject("endPage", endPage); // 해당 페이지 게시물 end
		mav.addObject("nowPage", nowPage); // 지금 페이지
		mav.setViewName("admin");
		return mav;
	}
	
	@RequestMapping(value="/trip/modify_admin.do", method=RequestMethod.GET)
	public ModelAndView adminModify(
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		ModelAndView mav= new ModelAndView();
		mav.setViewName("modify_admin");
		return mav;
	}
	@RequestMapping(value="/trip/insert_admin.do", method=RequestMethod.GET)
	public ModelAndView adminInsert(
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		ModelAndView mav= new ModelAndView();
		mav.setViewName("insert_admin");
		return mav;
	}
	
	@RequestMapping(value="/trip/insert_admin2.do", method=RequestMethod.POST)
	public void adminInsert2(
			@RequestParam("type") String type,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		if(type.equals("mem")) {
			
		} else if(type.equals("dorm")) {
			DormDTO dto = new DormDTO();
			dto.setDorm_no(Integer.parseInt(request.getParameter("dormno")));
			dto.setDorm_category_no(Integer.parseInt(request.getParameter("category")));
			dto.setDorm_name(request.getParameter("name"));
			String contents = request.getParameter("contents");
			String temp = contents.replace("\n", ",");
			contents = temp.replaceAll("  ", " ");
			System.out.println(contents);
			dto.setDorm_contents(contents);
			dto.setDorm_addr(request.getParameter("addr"));
			String pictureTemp = request.getParameter("picture");
			String picture = pictureTemp.substring(pictureTemp.lastIndexOf("\\")+1);
			System.out.println(picture);
			dto.setDorm_picture(picture);
			int wify = Integer.parseInt(request.getParameter("wifi"));
			int parking = Integer.parseInt(request.getParameter("parking"));
			int aircon = Integer.parseInt(request.getParameter("aircon"));
			int dryer = Integer.parseInt(request.getParameter("dryer"));
			int port = Integer.parseInt(request.getParameter("port"));
			
			dto.setOpt_wifi(wify);
			dto.setOpt_parking(parking);
			dto.setOpt_aircon(aircon);
			dto.setOpt_dryer(dryer);
			dto.setOpt_port(port);
			adminService.adminDormInsert(dto);
		}
	}
	
	@RequestMapping(value="/trip/update_admin.do", method=RequestMethod.POST)
	public void adminUpdate(
			@RequestParam("type") String type,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		if(type.equals("mem")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String tel = request.getParameter("tel");
			String authority = request.getParameter("authority");
			MemberDTO dto = new MemberDTO();
			dto.setMember_id(id);
			dto.setMember_names(name);
			dto.setMember_pw(pw);
			dto.setMember_tel(tel);
			dto.setMember_authority(authority);
			
			System.out.println("admin member업데이트 전 :"+dto.toString());
			
			adminService.adminMember(dto);
			System.out.println("업데이트 성공");
		} else if(type.equals("dorm")) {
			DormDTO dto = new DormDTO();
			dto.setDorm_no(Integer.parseInt(request.getParameter("dormno")));
			dto.setDorm_category_no(Integer.parseInt(request.getParameter("category")));
			dto.setDorm_name(request.getParameter("name"));
			dto.setDorm_contents(request.getParameter("contents"));
			dto.setDorm_addr(request.getParameter("addr"));
			dto.setDorm_picture(request.getParameter("picture"));
			int wify = Integer.parseInt(request.getParameter("wifi"));
			int parking = Integer.parseInt(request.getParameter("parking"));
			int aircon = Integer.parseInt(request.getParameter("aircon"));
			int dryer = Integer.parseInt(request.getParameter("dryer"));
			int port = Integer.parseInt(request.getParameter("port"));
			
			dto.setOpt_wifi(wify);
			dto.setOpt_parking(parking);
			dto.setOpt_aircon(aircon);
			dto.setOpt_dryer(dryer);
			dto.setOpt_port(port);
			System.out.println(dto.getOpt_wifi());
			adminService.adminDorm(dto);
		}
	}
	
	@RequestMapping(value="/trip/dormnoCheck.do", method=RequestMethod.POST)
	@ResponseBody
	public String dormnoCheck(@RequestParam("dormno") int dorm){
		String data = "0";
		int dormChecking = adminService.checkDormno(dorm);
		System.out.println(dormChecking);
		if(dormChecking >0)
			data = "1";
		return data;
	}
	
	@RequestMapping(value="/trip/delete_admin.do", method=RequestMethod.POST)
	public void adminDelete(
			@RequestParam("type") String type,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		if(type.equals("mem")) {
			String id = request.getParameter("id");
			memberService.removeMember(id);
			System.out.println("삭제 성공");
		} else if(type.equals("dorm")) {
			int dormno = Integer.parseInt(request.getParameter("dormno"));
			System.out.println("dorm 삭제 성공");
			adminService.adminDelDorm(dormno);
		}
	}
	
	//삭제하기
		@RequestMapping(value = "/trip/removeadminqna.do", method = RequestMethod.GET)
		public ModelAndView qna_adminremove(
				@RequestParam("admin_remove") int admin_remove,
				HttpServletRequest request, 
				HttpServletResponse response) throws Exception {

			ModelAndView mav= new ModelAndView();
		
			adminService.admindeleteArticle(admin_remove);
		
			mav.setViewName("close");
			mav.setViewName("redirect:admin.do");
			return mav;
		}
		
		//답변작성페이지
		@RequestMapping(value = "/trip/adminanswerqna.do", method = RequestMethod.GET)
		public ModelAndView qna_answer(
				@RequestParam("product_no") int product_no,
				HttpServletRequest request, 
				HttpServletResponse response) throws Exception {

		ModelAndView mav= new ModelAndView();

		List<QuestionDTO> QuestionList = new ArrayList<QuestionDTO>();
		
		QuestionList= adminService.adminselectQuestion(product_no);

		mav.addObject("questionList", QuestionList);
		mav.setViewName("qna_adminanswer");
		return mav;
		
		}
		
		//답변작성
		@RequestMapping(value = "/trip/adminreplyqna.do", method = RequestMethod.GET)
		public ModelAndView qna_answer(
				@RequestParam("adminrecontent") String adminrecontent,
				@RequestParam("adminparentNO") int adminparentNO,
				HttpServletRequest request, 
				HttpServletResponse response) throws Exception {

		ModelAndView mav= new ModelAndView();
		
			String id= (String)session.getAttribute("id");
			
			QuestionDTO qdto = new QuestionDTO();
			qdto.setQuestion_contents(adminrecontent);
			qdto.setQuestion_parentno(adminparentNO);
			qdto.setQuestion_title("☞");
			
			long miliseconds = System.currentTimeMillis();
	        Date date = new Date(miliseconds);
			qdto.setQuestion_date(date);
			qdto.setQuestion_picture("picture");
			qdto.setQuestion_view(0);
			qdto.setMember_id(id);
			
			adminService.admininsertReplyQuestion(qdto);
			mav.setViewName("close");
			return mav;
		
		}
		
		
	
}
