package com.co.spring02.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.spring02.service.BoardService;
import com.co.spring02.vo.BoardVO;
import com.co.spring02.vo.ResponseDto;

@RestController
@RequestMapping("/board/*")
public class BoardApiController {

	private static final Logger logger = LoggerFactory.getLogger(BoardApiController.class);

	@Inject 
	BoardService boardService;

	/*
	@RequestMapping(value="delete.do", method=RequestMethod.POST)
    public int delete(@RequestBody BoardVO vo, HttpSession session) throws Exception{
    	if(session.getAttribute("userId") != null) {
    		String writerid = (String) session.getAttribute("userId");
            vo.setWriterId(writerid);
    	}   	
    	return boardService.delete(vo);
    }
    */
	/*
    @RequestMapping("/test")
    public String test() {
        return "테스트";
    }
	@RequestMapping("/test2")
	public Map<String, Object> join() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "victolee");
		map.put("age", 26);
		
		return map;

	}
	*/
	/*
	@RequestMapping(value="delete.do", method=RequestMethod.POST)
    public boolean delete(@ModelAttribute BoardVO vo, HttpSession session) throws Exception{
    	if(session.getAttribute("userId") != null) {
    		String writerid = (String) session.getAttribute("userId");
            vo.setWriterId(writerid);
    	}   	
    	return ( boardService.delete(vo) != 0);
    }
    */
}
