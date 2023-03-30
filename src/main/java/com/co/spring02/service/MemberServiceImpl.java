package com.co.spring02.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.co.spring02.dao.MemberDAO;
import com.co.spring02.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	BCryptPasswordEncoder pwdEncoder;
	
	@Override
	public boolean loginCheck(MemberVO vo, HttpSession session) {
		MemberVO loginVo = memberDao.login(vo);
		boolean checkpw = pwdEncoder.matches(vo.getUserPw(), loginVo.getUserPw());
		if (checkpw) { // true일 경우 세션에 등록
			MemberVO vo2 = memberDao.MemberInfo(vo);
			// 세션 변수 등록
			session.setAttribute("userId", vo2.getUserId());
			session.setAttribute("userName", vo2.getUserName());
		}
		return checkpw;
	}

	@Override
	public MemberVO MemberInfo(MemberVO vo) {
		MemberVO loginVo = memberDao.login(vo);
		boolean checkpw = pwdEncoder.matches(vo.getUserPw(), loginVo.getUserPw());
		if(checkpw) {
			return memberDao.MemberInfo(vo);
		}else {
			return null;
		}
	}

	@Override
	public void logout(HttpSession session) throws Exception {
		if(session.getAttribute("loginToken") != null) {
			Date sessionLimit = new Date(System.currentTimeMillis()); // 로그인 유지기간 업데이트
			keepLoginValidUpdate((String)session.getAttribute("userId"), (String)session.getAttribute("loginToken"), sessionLimit);
		}
		session.invalidate();
	}

	@Inject
	private MemberDAO memberDao;

	@Override
	public List<MemberVO> memberList() throws Exception {
		return memberDao.memberList();
	}

	@Override
	public void insertMember(MemberVO vo) throws Exception {
		String inputpass = vo.getUserPw();
		String pwd = pwdEncoder.encode(inputpass);
		vo.setUserPw(pwd);
		memberDao.insertMember(vo);
	}

	@Override
	public MemberVO viewMember(String userId) throws Exception {
		return memberDao.viewMember(userId);
	}

	@Override
	public void deleteMember(String userId) throws Exception {
		memberDao.deleteMember(userId);
	}

	//@Override
	public boolean checkPw(String userId, String userPw) throws Exception {
		MemberVO vo = new MemberVO();
		vo.setUserId(userId);
		vo.setUserPw(userPw);
		MemberVO loginVo = memberDao.login(vo);
		boolean checkpw = pwdEncoder.matches(vo.getUserPw(), loginVo.getUserPw());
		return checkpw;
	}

	@Override
	public void updateMember(MemberVO vo) throws Exception {
		memberDao.updateMember(vo);
	}

	@Override
	public void keepLogin(String userId, String token, Date valid) throws Exception {
		memberDao.keepLogin(userId, token, valid);

	}

	@Override
	public MemberVO checkUserWithToken(String token) throws Exception {
		return memberDao.checkUserWithToken(token);
	}

	@Override
	public void keepLoginValidUpdate(String userId, String token, Date valid) throws Exception {
		memberDao.keepLoginValidUpdate(userId, token, valid);

	}

}
