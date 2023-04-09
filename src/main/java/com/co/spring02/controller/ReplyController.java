package com.co.spring02.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.co.spring02.service.BoardService;
import com.co.spring02.service.ReplyService;
import com.co.spring02.vo.Criteria;
import com.co.spring02.vo.PageMaker;
import com.co.spring02.vo.ReplyVO;

@RestController
@RequestMapping("/board/reply/*")
public class ReplyController {
    @Inject
    ReplyService replyService;
	@Inject 
	BoardService boardService;
    
    //댓글 입력
    @RequestMapping(method=RequestMethod.POST,value="insert.do")
    public boolean insert(@ModelAttribute ReplyVO vo, HttpSession session) throws Exception {
    	if(session.getAttribute("userId") != null) {
    		String userId = (String) session.getAttribute("userId");
    		String userName = (String) session.getAttribute("userName");
    		vo.setWriterId(userId);
    		vo.setWriter(userName);
    		vo.setLoginUser(true);
    	}else {
    		vo.setLoginUser(false);
    		//비회원이면서 패스워드가 비어있으면
    		if(vo.getPassword() == null || vo.getPassword().trim().isEmpty()) {
    			return false;
    		}
    	}
        int sucess = replyService.create(vo);
        if(sucess > 0 ) 
        	return true;
        else
        	return false;
    }
    
    //댓글 업데이트
    @RequestMapping(method=RequestMethod.POST,value="update.do")
    public boolean update(@ModelAttribute ReplyVO vo, HttpSession session) throws Exception {
    	if(session.getAttribute("userId") != null) {
    		String writerId = (String) session.getAttribute("userId");
            vo.setWriterId(writerId);
    	}  
    	int sucess =  replyService.update(vo);
        if(sucess > 0 ) 
        	return true;
        else
        	return false;
    }
    
    //댓글 삭제
    @RequestMapping(method=RequestMethod.POST,value="delete.do")
    public boolean delete(@ModelAttribute ReplyVO vo, HttpSession session) throws Exception {
    	if(session.getAttribute("userId") != null) {
    		String writerId = (String) session.getAttribute("userId");
            vo.setWriterId(writerId);
    	}
        int sucess = replyService.delete(vo);
        if(sucess > 0 ) 
        	return true;
        else
        	return false;
    }
    
    //REST방식으로 변경?
    //댓글 리스트
    @RequestMapping("list.do")
    //public Object list(@RequestParam(defaultValue="0") int bno) throws Exception{
    public Map<String, Object> list(
    		@RequestParam(defaultValue="0") int bno,
    		@RequestParam(defaultValue="1") int curReplyPage,
    		HttpSession session) throws Exception{
    	Map<String, Object> map = new LinkedHashMap<String, Object>();
    	
		if(bno < 1) {
	        //return "게시글이 존재하지 않습니다.";
			map.put("message", "게시글이 존재하지 않습니다.");
		}
		boolean ArticleExits =  boardService.checkArticle(bno);
		if(!ArticleExits) {
	        //return "게시글이 존재하지 않습니다.";
			map.put("message", "게시글이 존재하지 않습니다.");
		}
		Criteria replyCri = new Criteria();
		replyCri.setPage(curReplyPage);
		replyCri.setPerPageNum(20);
		String writerId = null;
    	if(session.getAttribute("userId") != null) {
    		writerId = (String) session.getAttribute("userId");
    	}
        List<ReplyVO> list = replyService.list(bno,writerId,replyCri);
        int count = replyService.count(bno);
    	map.put("replyCount", count);
    	map.put("replys", list);
	    PageMaker pages = new PageMaker();
	    pages.setCri(replyCri);
	    pages.setTotalCount(count);
    	map.put("pages", pages);	
    	return map;
    }
    
    //댓글 목록: View방식? //사용 예정x
    @RequestMapping("listView.do")
    public ModelAndView list(@RequestParam(defaultValue="0") int bno,  @RequestParam(defaultValue="1") int curReplyPage, 
    		HttpSession session, ModelAndView mav) throws Exception{
		Criteria replyCri = new Criteria();
		replyCri.setPage(curReplyPage);
		replyCri.setPerPageNum(20);
		String writerId = null;
    	if(session.getAttribute("userId") != null) {
    		writerId = (String) session.getAttribute("userId");
    	}
        List<ReplyVO> list = replyService.list(bno,writerId,replyCri);
        mav.setViewName("board/replyList");
        mav.addObject("list", list);
        return mav;
    }
}
