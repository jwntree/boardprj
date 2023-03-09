package com.co.spring02.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.co.spring02.service.MemberService;
import com.co.spring02.vo.MemberVO;


@Controller
@RequestMapping("/member/*")
public class MemberController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Inject	MemberService memberService;
	
	@Inject
	BCryptPasswordEncoder pwdEncoder;
	
	
	//로그인 화면
	@RequestMapping("login.do")
	public String login(HttpSession session,Model model) {
		//if(session.getAttribute("userId") == null) {
		//	return "member/login";
		//}else {
        //	return "redirect:/";
		//}
		return "member/login";
	}
	//로그인 체크
	@RequestMapping("loginCheck.do")
	public String loginCheck(@ModelAttribute MemberVO vo, HttpSession session,RedirectAttributes rttr,Model model) {
        boolean result = memberService.loginCheck(vo, session);
        if(result) {
        	model.addAttribute("msg", "success");
        	return "main";
        }else {
        	rttr.addFlashAttribute("msg", "failure");
			return "redirect:login.do";
        }
		
	}
	
	//로그아웃 처리
	@RequestMapping("logout.do")
	public String logout(HttpSession session,RedirectAttributes rttr,Model model) {
        memberService.logout(session);
    	rttr.addFlashAttribute("msg", "logout");
		return "redirect:login.do";
	}
	
	
	/*
	// 01 회원 목록
    // url pattern mapping
    @RequestMapping("/list.do")
    public String memberList(Model model) throws Exception{
    // controller => service => dao 요청
        List<MemberVO> list = memberService.memberList();
        model.addAttribute("list", list);
        return "member/member_list";
    }
    */
    
    // 02_01 회원 등록 페이지로 이동
    // url pattern mapping
    @RequestMapping("member/write.do")
    public String memberWrite(Model model){
        return "member/member_write";
    }
    
    // 02_02 회원 등록 처리 후 ==> 회원목록으로 리다이렉트
    // @ModelAttribute에 폼에서 입력한 데이터가 저장된다.
    @RequestMapping("/insert.do")
    // * 폼에서 입력한 데이터를 받아오는 법 3가지 
    //public String memberInsert(HttpServlet request){
    //public String memberInsert(String userId, String userPw, String userName, String userEmail){
    public String memberInsert(@ModelAttribute MemberVO vo) throws Exception{
        // 테이블에 레코드 입력
        memberService.insertMember(vo);
        // * (/)의 유무에 차이
        // /member/list.do : 루트 디렉토리를 기준
        // member/list.do : 현재 디렉토리를 기준
        // member_list.jsp로 리다이렉트
        //return "redirect:/member/list.do";
        return "redirect:/";
    }
    
    @RequestMapping("/view.do")
    public String memberView(String userId, Model model)  throws Exception{
    	if(userId == null || userId.isEmpty()) {
            //return "redirect:/member/list.do";
            return "redirect:/";
    	}
        model.addAttribute("dto", memberService.viewMember(userId));
        logger.info("클릭한 아이디 : "+userId);
    	return "member/member_view";
    }
    
    @RequestMapping("/updateView.do")
    public String memberView( Model model,HttpSession session)  throws Exception{

    	String UserId = (String) session.getAttribute("userId");
    	if(UserId != null) {
    		model.addAttribute("dto", memberService.viewMember(UserId));
    	}
    	return "member/member_view";
    }
    
    
    @RequestMapping(value ="member/update.do",method= RequestMethod.POST)
    public String memberUpdate(@ModelAttribute MemberVO vo, RedirectAttributes rttr,HttpSession session, Model model) throws Exception{
        boolean result = memberService.checkPw(vo.getUserId(), vo.getUserPw());
        if(result) {
        	memberService.updateMember(vo);
        	MemberVO vo2 = memberService.viewMember(vo.getUserId());
        	
        	//유저id와 세션의 id가 같으면 유저네임 업데이트
        	if(session.getAttribute("userId").equals(vo.getUserId())) {
        		session.setAttribute("userName", vo2.getUserName());
        	}
        	//return "redirect:/member/list.do";
            return "redirect:/";

        }else {
            /*
        	MemberVO vo2 = memberService.viewMember(vo.getUserId());
            vo.setRegDate(vo2.getRegDate());
            vo.setUpdateDate(vo2.getUpdateDate());
            model.addAttribute("dto", vo);
            model.addAttribute("message", "비밀번호 불일치");
            return "member/member_view";
            */
        	/*
        	MemberVO vo2 = memberService.viewMember(vo.getUserId());
            model.addAttribute("dto", vo2);
            model.addAttribute("message", "비밀번호 불일치");
            return "member/member_view";
            */
        	
			rttr.addAttribute("userId", vo.getUserId());
			rttr.addFlashAttribute("message", "비밀번호 불일치");
			return "redirect:view.do";
        }
    }
    @RequestMapping("member/delete.do")
    public String memberDelete(@RequestParam String userId, String userPw, RedirectAttributes rttr, Model model) throws Exception{
        boolean result = memberService.checkPw(userId, userPw);
        if(result) {
            memberService.deleteMember(userId);
            //return "redirect:/member/list.do";
            return "redirect:/";
        }else {
            //model.addAttribute("message", "비밀번호 불일치");
            //model.addAttribute("dto", memberService.viewMember(userId));
            //return "member/member_view";
			rttr.addAttribute("userId", userId);
			rttr.addFlashAttribute("message", "비밀번호 불일치");
            return "redirect:/member/view.do";
        	} 
        }
}
