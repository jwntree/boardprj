package com.co.spring02.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.co.spring02.dao.MemberDAO;
import com.co.spring02.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Override
	public boolean loginCheck(MemberVO vo, HttpSession session) {
        boolean result = memberDao.loginCheck(vo);
        if (result) { // true일 경우 세션에 등록
            MemberVO vo2 = MemberInfo(vo);
            // 세션 변수 등록
            session.setAttribute("userId", vo2.getUserId());
            session.setAttribute("userName", vo2.getUserName());
        } 
        return result;
	}

	@Override
	public MemberVO MemberInfo(MemberVO vo) {
        return memberDao.MemberInfo(vo);
	}

	@Override
	public void logout(HttpSession session) {
        session.invalidate();		
	}
	
	@Inject
	private MemberDAO memberDao;
	
    @Override
    public List<MemberVO> memberList() throws Exception{
        return memberDao.memberList();
    }
 
    @Override
    public void insertMember(MemberVO vo) throws Exception{
    	memberDao.insertMember(vo);
    }
 
    @Override
    public MemberVO viewMember(String userId) throws Exception{
        return memberDao.viewMember(userId);
    }
 
    @Override
    public void deleteMember(String userId) throws Exception{
        memberDao.deleteMember(userId);
    }
 
    @Override
    public boolean checkPw(String userId, String userPw) throws Exception{
        return memberDao.checkPw(userId, userPw);
    }

	@Override
	public void updateMember(MemberVO vo) throws Exception{
        memberDao.updateMember(vo);		
	}



}
