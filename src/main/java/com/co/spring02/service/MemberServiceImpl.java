package com.co.spring02.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.co.spring02.dao.MemberDAO;
import com.co.spring02.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	@Inject
	private MemberDAO memberDao;
	
    @Override
    public List<MemberVO> memberList() {
        return memberDao.memberList();
    }
 
    @Override
    public void insertMember(MemberVO vo) {
        
    }
 
    @Override
    public MemberVO viewMember() {
        return null;
    }
 
    @Override
    public void deleteMember(String userId) {
        
    }
 
    @Override
    public void updateMember(MemberVO vo) {
        
    }

}
