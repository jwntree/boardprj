package com.co.spring02.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.co.spring02.service.BoardService;
import com.co.spring02.service.ReplyService;
import com.co.spring02.vo.BoardVO;
import com.co.spring02.vo.Criteria;
import com.co.spring02.vo.PageMaker;
import com.co.spring02.vo.ResponseDto;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Inject 
	BoardService boardService;
	@Inject
	ReplyService replyService;
	
	//게시글 리스트
	@RequestMapping(value="list.do")
	public String list(@RequestParam(defaultValue="") String searchOption,
            @RequestParam(defaultValue="") String keyword,
            @RequestParam(defaultValue="1") int curPage,
            @RequestParam(defaultValue="30") int perPage,
            Model model) throws Exception{
		Criteria cri = new Criteria();
		cri.setPage(curPage);
		cri.setPerPageNum(perPage);
		List<BoardVO> list = boardService.list(searchOption, keyword,cri);
	    int count = boardService.countArticle(searchOption, keyword);
		
	    PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(count);
		
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("searchOption", searchOption);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("curPage", curPage);
		model.addAttribute("perPage", perPage);

		return "board/list";
	}
	
	//게시글 작성화면
	@RequestMapping(value="write.do", method=RequestMethod.GET)
	public String write() throws Exception{
        return "board/write";
	}
	
	//게시글 작성처리
    @RequestMapping(value="insert.do", method=RequestMethod.POST)
    public String insert(@ModelAttribute BoardVO vo, HttpSession session) throws Exception{
        
    	//TODO: 코드를 서비스로 이동
    	// 로그인 되었을 경우 session에 저장된 userId를 writer에 저장
    	if(session.getAttribute("userId") != null) {
    		String writerId = (String) session.getAttribute("userId");
    		String writerName = (String) session.getAttribute("userName");
            vo.setWriterId(writerId);
            vo.setWriter(writerName);
            vo.setLoginUser(true);
    	}else {
    		
            vo.setLoginUser(false);
    	}
        boardService.create(vo);
        return "redirect:list.do";
    }
    
    //게시글 조회
    
	@RequestMapping(value="view.do", method=RequestMethod.GET)
	public String view(@RequestParam(defaultValue = "-1") int bno, Model model) throws Exception{
		//TODO: Redirection to Error Page
		if(bno < 1) {
	        return "redirect:list.do";
		}
		BoardVO vo =  boardService.read(bno);
		if(vo == null) {
			//logger.debug("게시글이 없습니다.");
	        return "redirect:list.do";
		}else {
			//logger.debug("게시글이 존재합니다.");
			boardService.increaseViewcnt(bno);
			int replyCount = replyService.count(bno);
			String newContent = vo.getContent().replaceAll("\n", "<br>"); //TODO: Raw Text를 받아 개행문자를 치환하여 표시하는대신 에디터로 html을 받아 db에 저장
			vo.setContent(newContent);
			model.addAttribute("dto", vo);
			model.addAttribute("replyCount", replyCount);
	        return "board/view";
		}
	}
	
    
    //게시글 수정
    
    @RequestMapping(value="updateView.do")
    public String updateView(@RequestParam(defaultValue = "-1") int bno, Model model) throws Exception{
		if(bno < 1) {
	        return "redirect:list.do";
		}
    	BoardVO vo =  boardService.read(bno);
		model.addAttribute("dto", vo);
        return "board/update";
    }
    
    /*
    @RequestMapping(value="update.do", method=RequestMethod.POST)
    public String update(@ModelAttribute BoardVO vo, HttpSession session) throws Exception{
    	if(session.getAttribute("userId") != null) {
    		String writerId = (String) session.getAttribute("userId");
            vo.setWriterId(writerId);
    	}
        boardService.update(vo);
        return "redirect:list.do";
    }
    */
	@ResponseBody
	@RequestMapping(value="update.do", method=RequestMethod.POST)
    public boolean update(@ModelAttribute BoardVO vo, HttpSession session) throws Exception{
    	if(session.getAttribute("userId") != null) {
    		String writerId = (String) session.getAttribute("userId");
            vo.setWriterId(writerId);
    	}   	
    	return ( boardService.update(vo) != 0);
    }
     
	
    //게시글 삭제
	
    @RequestMapping(value="deleteView.do")
    public String deleteView(@RequestParam(defaultValue = "-1") int bno, Model model) throws Exception{
		if(bno < 1) {
	        return "redirect:list.do";
		}
		model.addAttribute("bno", bno);
        return "board/delete";
    }
    
    /*
    @RequestMapping(value="delete.do", method=RequestMethod.POST)
    public String delete(@ModelAttribute BoardVO vo, HttpSession session) throws Exception{
    	if(session.getAttribute("userId") != null) {
    		String writerId = (String) session.getAttribute("userId");
            vo.setWriterId(writerId);
    	}
        boardService.delete(vo);
        return "redirect:list.do";
    }
    */
    
	@ResponseBody
	@RequestMapping(value="delete.do", method=RequestMethod.POST)
    public boolean delete(@ModelAttribute BoardVO vo, HttpSession session) throws Exception{
    	if(session.getAttribute("userId") != null) {
    		String writerId = (String) session.getAttribute("userId");
            vo.setWriterId(writerId);
    	}   	
    	return ( boardService.delete(vo) != 0);
    }
     
    
    
    
    
    
    
}
