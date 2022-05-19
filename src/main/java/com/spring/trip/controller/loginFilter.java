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
		
		System.out.println("Filter doFileter() 시작 ------------------------------------");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		
		
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
        System.out.println("PrintURLFilter doFileter() 끝 ------------------------------------");
	}

	@Override
	public void destroy() {

	}

}
