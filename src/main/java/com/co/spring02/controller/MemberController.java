package com.co.spring02.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;
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
		return "member/login";
	}
	//로그인 체크
	@RequestMapping("loginCheck.do")
	public String loginCheck(@ModelAttribute MemberVO vo, boolean autologin, HttpSession session,RedirectAttributes rttr,
			Model model, HttpServletResponse response) throws Exception {
        boolean result = memberService.loginCheck(vo, session);
        if(result) {
            if ( autologin ){
                // 오토로그인이 체크되어 있으면 쿠키를 생성하고 현재 세션의 id를 쿠키에 저장한다.
                Cookie cookie = new Cookie("loginCookie", session.getId());
				session.setAttribute("loginToken", session.getId());
                cookie.setPath("/");
                cookie.setMaxAge(60*60*24*7);
                response.addCookie(cookie);
                int amount = 60 * 60 * 24 * 7;  // 7일
                Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount)); // 로그인 유지기간 설정
                memberService.keepLogin(vo.getUserId(), session.getId(), sessionLimit);

            }
        	rttr.addFlashAttribute("msg", "success");
        	return "redirect:/";
        }else {
        	rttr.addFlashAttribute("msg", "failure");
			return "redirect:login.do";
        }
		
	}
	
	//로그아웃 처리
	@RequestMapping("logout.do")
	public String logout(HttpSession session,RedirectAttributes rttr,Model model, HttpServletResponse response) throws Exception {
        memberService.logout(session);
        //쿠키삭제
        Cookie cookie = new Cookie("loginCookie", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
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
        boolean result = memberService.insertMember(vo);
        // * (/)의 유무에 차이
        // /member/list.do : 루트 디렉토리를 기준
        // member/list.do : 현재 디렉토리를 기준
        // member_list.jsp로 리다이렉트
        //return "redirect:/member/list.do";
        //return "redirect:/member/login.do";
        if(result)
        	return "redirect:/member/login.do";
        else
        	return "redirect:/member/SignupFail.do";
    }
    
 // 아이디 중복 체크

    @ResponseBody
    @RequestMapping(value="/idChk", method = RequestMethod.POST)
    public int idChk(String UserId) throws Exception {
    	int result = memberService.checkIdExist(UserId);
    	return result;
    }
    
    @RequestMapping("member/SignupFail.do")
    public String SignupFail(Model model){
        return "member/SignupFail";
    }
    
    @RequestMapping("/info.do")
    public String memberInfo(String userId, Model model)  throws Exception{
    	if(userId == null || userId.isEmpty()) {
            return "redirect:/";
    	}
        model.addAttribute("dto", memberService.viewMember(userId));
        logger.info("클릭한 아이디 : "+userId);
    	return "member/member_info";
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
            return "redirect:/";

        }else {      	
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
            return "redirect:/";
        }else {
			rttr.addAttribute("userId", userId);
			rttr.addFlashAttribute("message", "비밀번호 불일치");
            return "redirect:/member/view.do";
        	} 
        }
}
