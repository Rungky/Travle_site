package com.spring.trip.controller;

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
		
		ModelAndView mav= new ModelAndView();
		MemberDTO memberDTO = memberService.select_myMember(member_id);
		mav.addObject("memberDTO",memberDTO);
		List <MemberDTO> membersList = adminService.allMembers();
		mav.addObject("membersList", membersList);
		List <DormDTO> dormslist = adminService.allDormsList();
		mav.addObject("dormsList", dormslist);
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
			MemberDTO dto = new MemberDTO();
			dto.setMember_id(id);
			dto.setMember_names(name);
			dto.setMember_pw(pw);
			dto.setMember_tel(tel);
			
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
}
