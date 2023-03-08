package com.co.spring02.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.co.spring02.service.BoardService;
import com.co.spring02.vo.BoardVO;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Inject 
	BoardService boardService;
	
	//게시글 리스트
	@RequestMapping(value="list.do", method=RequestMethod.GET)
	public String list(Model model) throws Exception{
		List<BoardVO> list = boardService.list();
		model.addAttribute("list", list);
        return "board/list";
	}
	
	//게시글 작성화면
	@RequestMapping(value="write.do", method=RequestMethod.GET)
	public String write() throws Exception{
        return "board/write";
	}
	
	//게시글 작성처리
    @RequestMapping(value="insert.do", method=RequestMethod.POST)
    public String insert(@ModelAttribute BoardVO vo) throws Exception{
        boardService.create(vo);
        return "redirect:list.do";
    }
    
    //게시글 조회
    
	@RequestMapping(value="view.do", method=RequestMethod.GET)
	public String view(@Valid int bno, Model model) throws Exception{
		boardService.increaseViewcnt(bno);
		BoardVO vo =  boardService.read(bno);
		model.addAttribute("dto", vo);
        return "board/write";
	}
	
    
    //게시글 수정
    
    @RequestMapping(value="update.do", method=RequestMethod.POST)
    public String update(@ModelAttribute BoardVO vo) throws Exception{
        boardService.update(vo);
        return "redirect:list.do";
    }
    //게시글 삭제
	
    @RequestMapping(value="delete.do", method=RequestMethod.POST)
    public String delete(@RequestParam int bno) throws Exception{
        boardService.delete(bno);
        return "redirect:list.do";
    }
    
    
    
    
    
    
    
    
}
