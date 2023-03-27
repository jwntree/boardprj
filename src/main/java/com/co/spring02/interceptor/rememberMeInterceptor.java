package com.co.spring02.interceptor;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.co.spring02.service.MemberService;
import com.co.spring02.vo.MemberVO;

public class rememberMeInterceptor extends HandlerInterceptorAdapter{

    private static final Logger logger = LoggerFactory.getLogger(rememberMeInterceptor.class);

    @Inject
    private MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        
		if (session.getAttribute("userId") == null) {
			Cookie autologin = WebUtils.getCookie(request, "loginCookie");
			if (autologin != null) {
				MemberVO vo = memberService.checkUserWithToken(autologin.getValue());
				if (vo != null) {
					session.setAttribute("userId", vo.getUserId());
					session.setAttribute("userName", vo.getUserName());
					session.setAttribute("loginToken", autologin.getValue());
					if(autologin.getMaxAge() * 1000 < System.currentTimeMillis() - (60 * 60 * 24 * 1) * 1000) { //less than 1days
						int amount = 60 * 60 * 24 * 3; // 3일
						Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount)); // 로그인 유지기간 업데이트
						memberService.keepLoginValidUpdate(vo.getUserId(), autologin.getValue(), sessionLimit);
					}
				}
			}
		}

        
        return true;
    }
}
