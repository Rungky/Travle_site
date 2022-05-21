package com.spring.trip.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.trip.dto.DormVO;
import com.spring.trip.dto.MemberDTO;
import com.spring.trip.service.MemberService;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired // 의존성 주입함
	private MemberService memberService;

	// 로그인 페이지 이동
	@RequestMapping(value = "/trip/login.do", method = RequestMethod.GET)
	public String loginForm(Model model, HttpServletRequest req, HttpServletResponse response) throws Exception{
		String referer = req.getHeader("referer");
		req.getSession().setAttribute("referer", referer);
		model.addAttribute("referer", referer);
		System.out.println("컨트롤러 referer : " + referer);
		return "login";
	}

	// 회원가입 페이지 접속
	@RequestMapping(value = "/trip/signup.do", method = RequestMethod.GET)
	public String JoinForm() {
		logger.info("get 회원가입 메소드 진입");
		return "signup";
	}

	// 아이디 찾기 페이지 접속
	@RequestMapping(value = "/trip/idFind.do", method = RequestMethod.GET)
	public String idFindForm() {
		logger.info("get 아이디 찾기 메소드 진입");
		return "idFind";
	}

	// 비밀번호 찾기 페이지 접속
	@RequestMapping(value = "/trip/pwFind.do", method = RequestMethod.GET)
	public String pwFindForm() {
		logger.info("get 비밀번호 찾기 메소드 진입");
		return "pwFind";
	}
	// ------------------기능 메소드----------------------

	// 로그인 기능
	@RequestMapping(value = "/trip/loginCheck.do", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute MemberDTO dto, HttpServletRequest req) throws Exception {
		int msg = 1;
		logger.info("post login");

		HttpSession session = req.getSession();
		System.out.println("Controller  member_id : " + dto.getMember_id() + " member_pw : " + dto.getMember_pw());
		if (dto == null) {
			session.setAttribute("id", null);
			ModelAndView mav = new ModelAndView("redirect:/trip/login.do");
			mav.addObject("msg", msg);
			return mav;
		} else {
			MemberDTO login = memberService.login(dto);

			if (login == null) {
				session.setAttribute("id", null);
				ModelAndView mav = new ModelAndView("redirect:/trip/login.do");
				mav.addObject("msg", msg);
				return mav;
			} else {
				String refererURL = (String) session.getAttribute("referer");
				System.out.println("test : " + refererURL);
				
				//login이라는 단어가 포함되어 있지 않은 경우
				if(refererURL.indexOf("login") != -1 || refererURL.indexOf("/trip") != -1) {
					session.setAttribute("id", login.getMember_id());
					
					String[] refererSplit = refererURL.split("/trip");
					String url = "";
					for(String test : refererSplit) {
						System.out.println("refererSplit : " + test);
						
						url = test;
					}
					
					url = url.substring(1);
					String url_2 = "trip/";
					
					ModelAndView mav = new ModelAndView("redirect:" + url);
					System.out.println("url : " + url);
					
					
					return mav;
				} else if(refererURL.indexOf("login") == -1 || refererURL.indexOf("/trip") != -1){  //login 이라는 단어가 포함되어 있는 경우 
					session.setAttribute("id", login.getMember_id());
					ModelAndView mav = new ModelAndView("redirect:/trip/main.do");
					return mav;
				} else {
					return null;
				}
			}
		}
	}

	// 회원가입 기능
	@RequestMapping(value = "/trip/signupCheck.do", method = RequestMethod.POST)
	public String postJoin(@ModelAttribute MemberDTO dto) throws Exception {
		logger.info("post 회원가입 메소드 진입");
		System.out.println("회원가입" + dto.getMember_id());
		System.out.println("회원가입" + dto.getMember_pw());
		System.out.println("회원가입" + dto.getMember_names());
		System.out.println("회원가입" + dto.getMember_tel());
		MemberDTO result = memberService.join(dto);
		if (result == null) {
			logger.info("controller if문   member_id : " + dto.getMember_id());
			logger.info("controller if문   member_pw : " + dto.getMember_pw());
			return "signup";
		} else {
			logger.info("controller else문    member_id : " + result.getMember_id());
			logger.info("controller else문    member_pw : " + result.getMember_pw());
			return "login";
		}
	}

	// 아이디 찾기 기능
	@RequestMapping(value = "/trip/idFindCheck.do", method = RequestMethod.POST)
	public String idFind(@ModelAttribute MemberDTO dto, Model model) throws Exception {
		logger.info("post 아이디 찾기 메소드 진입");
		MemberDTO result = memberService.idFind(dto);
		System.out.println("아이디 찾기" + result.getMember_id());
		if (result == null) {
			logger.info("controller if문   member_id : " + result.getMember_id());

		} else {
			logger.info("controller else문    member_id : " + result.getMember_id());
			model.addAttribute("member_id", result.getMember_id());
		}
		return "idFind_result";
	}

	
	// 로그아웃
	@RequestMapping(value = "/trip/logoutCheck.do", method = RequestMethod.GET)
	public ModelAndView  logout(HttpServletRequest request) throws Exception {

		logger.info("logoutMainGET메서드 진입");

		HttpSession session = request.getSession();
		session.invalidate();

		ModelAndView mav = new ModelAndView("redirect:/trip/main.do");
		return mav;
	}


	// 비밀번호 찾기 기능
	@RequestMapping(value = "/trip/pwFindCheck.do", method = RequestMethod.POST)
	public String pwFind(@ModelAttribute MemberDTO dto, Model model, HttpSession session) throws Exception {
		logger.info("post 비밀번호 찾기 메소드 진입");
		MemberDTO result = memberService.pwFind(dto);

		if (result == null) {
			logger.info("controller if문   member_pw : " + dto.getMember_pw());
			System.out.println("비밀번호 찾기" + dto.getMember_id());
			System.out.println("비밀번호 찾기" + dto.getMember_pw());
		} else {
			System.out.println("비밀번호 찾기" + result.getMember_pw());
			System.out.println("아이디" + result.getMember_id());
			logger.info("controller else문    member_pw : " + result.getMember_pw());
			session.setAttribute("id", dto.getMember_id());
			model.addAttribute("member_pw", result.getMember_pw());
		}
		return "pwFind_result";
	}

	// 새 비밀번호 변경하기
	@RequestMapping(value = "/trip/newPw.do", method = RequestMethod.POST)
	public String newPw(@ModelAttribute MemberDTO dto, HttpSession session, Model model, HttpServletRequest req)
			throws Exception {
		dto.setMember_id((String) session.getAttribute("id"));
		System.out.println(session.getAttribute("id"));

		System.out.println("세션에 저장한 아이디 : " + session.getAttribute("id"));
		logger.info("세션에 저장한 아이디 : " + session.getAttribute("id"));
		model.addAttribute("member_pw", dto.getMember_pw());
		model.addAttribute("member_id", session.getAttribute("id"));

		int result = memberService.newPw(dto);

		return "login";
	}

// 마이페이지
	@RequestMapping(value = "/trip/mypage.do", method = RequestMethod.GET)
	public ModelAndView mypage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("id");
		System.out.println("member_id ="+member_id);
		MemberDTO memberDTO = memberService.select_myMember(member_id);		
		
		System.out.println("마이페이지값 :"+memberDTO.toString());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypage");
		mav.addObject("member", memberDTO);
		return mav;
	}
	
	// 회원정보 수정 
		@RequestMapping(value = "/trip/modifyMember.do", method = RequestMethod.POST)
		public ModelAndView modifyMember(HttpServletRequest request, HttpServletResponse response,
				@RequestParam("member_id") String member_id)
				throws Exception {

			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setMember_id(member_id);
			
			if(request.getParameter("member_name") != null) {
				String member_name = request.getParameter("member_name");
				memberDTO.setMember_names(member_name);
			}
			if(request.getParameter("member_tel") != null) {
				String member_tel = request.getParameter("member_tel");
				memberDTO.setMember_tel(member_tel);
			}
			if(request.getParameter("member_pw") != null) {
				String member_pw = request.getParameter("member_pw");
				memberDTO.setMember_pw(member_pw);
			}
		
			String test = memberDTO.toString();
			System.out.println("test:"+test);
			memberService.modifyMember(memberDTO);
			System.out.println("memerModify DAO 실행 완료 ");
			ModelAndView mav = new ModelAndView("redirect:/trip/mypage.do");
			return mav;
		}
	

//회원 탈퇴
	@RequestMapping(value = "/trip/removeMember.do", method = RequestMethod.POST)
	public ModelAndView removeMember(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("member_id") String member_id) throws Exception {
		HttpSession session = request.getSession();
		// String member_id = (String) session.getAttribute("id");
		memberService.removeMember(member_id);
<<<<<<< HEAD
		session.invalidate();
=======
		System.out.println("2");
>>>>>>> ec65eb2384e2a7c5f1ba3b95bbe2d889b9ec6a9a
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/trip/main.do");
		return mav;
	}

// 관심숙소
	@RequestMapping(value = "/trip/myLike.do", method = RequestMethod.GET)
	public ModelAndView myLike(HttpServletRequest request, HttpServletResponse response
			) throws Exception {
		////@RequestParam("member_id") String member_id
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("id");
		
		Calendar cal = Calendar.getInstance();
		String format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar calendar = new GregorianCalendar();
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
		
		String reserve_checkin = SDF.format(calendar.getTime());		
		calendar.add(Calendar.DATE, +1);	
		String reserve_checkout = SDF.format(calendar.getTime());		
		
		List<DormVO> dorm_list = new ArrayList<DormVO>();
		dorm_list = memberService.selectList_likeDorm(member_id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("myLike");
		mav.addObject("dorm_list", dorm_list);
		mav.addObject("reserve_checkin", reserve_checkin);
		mav.addObject("reserve_checkout", reserve_checkout);
		return mav;
	}
		
	

}
