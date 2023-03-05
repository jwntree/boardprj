package com.co.spring02.controller;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.co.spring02.service.MemberService;
import com.co.spring02.vo.MemberVO;

import java.util.List;


@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Inject	MemberService memberService;
	
	@Inject
	BCryptPasswordEncoder pwdEncoder;
	
	
	// 01 회원 목록
    // url pattern mapping
    @RequestMapping("member/list.do")
    public String memberList(Model model){
    // controller => service => dao 요청
        List<MemberVO> list = memberService.memberList();
        model.addAttribute("list", list);
        return "member/member_list";
    }
	
}
