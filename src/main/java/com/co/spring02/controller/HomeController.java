package com.co.spring02.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/*
	@ResponseBody
	@RequestMapping("/hi")
	public String join() {
		return "하이하이";
	}
	*/
	   @RequestMapping("/")
	    public String main(Model model) throws Exception{
	        // model : 데이터를 담는 그릇 역할, map구조로 저장된다.
	        // model.addAttribute("변수명", 값);
	        model.addAttribute("msg", "홈페이지 방문을 환영합니다!");
	        return "main"; // main.jsp로 포워딩
	    }
	   
	    @RequestMapping(value = "home.do", method = RequestMethod.GET)
	    public String home(Locale locale, Model model) throws Exception {
	        logger.info("Welcome home! The client locale is {}.", locale);
	        
	        Date date = new Date();
	        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
	        
	        String formattedDate = dateFormat.format(date);
	        
	        model.addAttribute("serverTime", formattedDate );
	        
	        return "home";
	    }
	    
	
}
