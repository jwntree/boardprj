package com.co.spring02.dao;

import java.util.List;

import com.co.spring02.vo.MemberVO;

public interface MemberDAO {
	 // 회원 목록 
    public List<MemberVO> memberList() throws Exception;
    // 회원 입력
    public void insertMember(MemberVO vo) throws Exception;
    // 회원 정보 상세보기
    public MemberVO viewMember(String userId) throws Exception;
    // 회원삭제
    public void deleteMember(String userId) throws Exception;
    // 회원정보 수정
    public void updateMember(MemberVO vo) throws Exception;
	//비밀번호 체크
    boolean checkPw(String userId, String userPw) throws Exception;
}
