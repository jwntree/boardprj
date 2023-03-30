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
	
	   @RequestMapping("/")
	    public String main(Model model) throws Exception{
	        model.addAttribute("msg", "홈페이지 방문을 환영합니다!");
	        return "main";
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
