package com.co.spring02.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.co.spring02.vo.MemberVO;

@Repository
public class MemberDAOimpl implements MemberDAO{

	@Inject
    SqlSession sqlSession;
	
    // 회원목록
    @Override
    public List<MemberVO> memberList() {
        return sqlSession.selectList("memberMapper.memberList");
    }
 
    @Override
    public void insertMember(MemberVO vo) {
    
    }
 
    @Override
    public MemberVO viewMember() {
        // TODO Auto-generated method stub
        return null;
    }
 
    @Override
    public void deleteMember(String userId) {
        // TODO Auto-generated method stub
 
    }
 
    @Override
    public void updateMember(MemberVO vo) {
        // TODO Auto-generated method stub
 
    }
	

}
