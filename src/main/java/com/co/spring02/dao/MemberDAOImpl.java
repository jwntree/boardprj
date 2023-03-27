package com.co.spring02.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.co.spring02.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Inject
    SqlSession sqlSession;

	@Override
	public boolean loginCheck(MemberVO vo) {
		return sqlSession.selectOne("memberMapper.loginCheck", vo);
	}

	@Override
    public MemberVO MemberInfo(MemberVO vo) {
        return sqlSession.selectOne("memberMapper.MemberInfo", vo);
	}

    // 회원목록
    @Override
    public List<MemberVO> memberList() throws Exception {
        return sqlSession.selectList("memberMapper.memberList");
    }
 
    @Override
    public void insertMember(MemberVO vo) throws Exception {
    	sqlSession.insert("memberMapper.insertMember",vo);
    }
 
    @Override
    public MemberVO viewMember(String userId) throws Exception {
        return sqlSession.selectOne("memberMapper.viewMember", userId);
    }
 
    @Override
    public void deleteMember(String userId) throws Exception {
        sqlSession.delete("memberMapper.deleteMember", userId);
 
    }
 
    @Override
    public void updateMember(MemberVO vo) throws Exception {
        sqlSession.update("memberMapper.updateMember", vo);
    }
    @Override
    public boolean checkPw(String userId, String userPw) {
        boolean result = false;
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);
        map.put("userPw", userPw);
        int count = sqlSession.selectOne("memberMapper.checkPw", map);
        if(count == 1) result= true;
        return result;
    }

	@Override
	public void keepLogin(String userId, String token, Date valid) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
        map.put("token", token);
        map.put("valid", valid);
        sqlSession.update("memberMapper.insertKeepLogin", map);
		
	}

	@Override
	public MemberVO checkUserWithToken(String token) throws Exception {
		return sqlSession.selectOne("memberMapper.checkUserWithToken", token);
	}

	@Override
	public void keepLoginValidUpdate(String userId, String token, Date valid) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("token", token);
        map.put("valid", valid);
        sqlSession.update("memberMapper.keepLoginValidUpdate", map);
		
	}


}
