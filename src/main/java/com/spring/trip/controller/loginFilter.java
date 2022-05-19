package com.spring.trip.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class loginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("PrintURLFilter doFileter() 시작 ------------------------------------");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//		System.out.println("요청 URL: " + req.getRequestURI() + " --------------------------------");
		
		
		String path = ((HttpServletRequest) req).getRequestURI();
		System.out.println("path : "+path);
        if (path.contains("login.do") 
				  || path.contains("loginCheck.do") 
				  || path.contains("signup.do") 
				  || path.contains("idFind.do")
				  || path.contains("pwFind.do")
				  || path.contains("signupCheck.do")
				  || path.contains("idFindCheck.do")
				  || path.contains("pwFindCheck.do")
				  || path.contains("newPw.do")
				  || path.contains("main.do")
				  ) { 
        	chain.doFilter(request, response);
            // 제외한 url이 들어 왔을때 동작할 코드 작성
        } else {
            // 원래의 필터 기능 동작을 할 코드 작성
        	if (session.getAttribute("id") != null) {
				  chain.doFilter(request, response);
			  } else if(session.getAttribute("id") == null) {
				  httpResponse.sendRedirect("login.do");
			  }
           }
		
        chain.doFilter(request, response);
        System.out.println("PrintURLFilter doFileter() 끝 ------------------------------------");
//		System.out.println("doFilter 호출함");
//		
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//		HttpSession session = httpRequest.getSession();
//		
//		String action = request.getParameter("action");
//		String path1 = httpRequest.getRequestURI();
//		System.out.println("action : " + action);
//		System.out.println("path1 : " + path1);
//		
//		  if (action == null 
//				  || action.equals("login.do") 
//				  || action.equals("loginForm.do") 
//				  || action.equals("joinForm.do") 
//				  || action.equals("join.do")
//				  || action.equals("detail.do")
//				  || action.equals("reservation.do")
//				  || action.equals("noReservation.do")
//				  || action.equals("signup.do")
//				  || action.equals("main.do")) {
//			  chain.doFilter(request, response);
//			  
//		  } else{
//			  if (session.getAttribute("id") != null) {
//				  chain.doFilter(request, response);
//			  } else if(session.getAttribute("id") == null) {
//				  httpResponse.sendRedirect(path1+"?action="+"loginForm.do");
//			  }
//		  }

	}

	@Override
	public void destroy() {

	}

}
