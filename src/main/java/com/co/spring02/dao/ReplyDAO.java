package com.co.spring02.dao;

import java.util.List;

import com.co.spring02.vo.Criteria;
import com.co.spring02.vo.ReplyVO;


public interface ReplyDAO {
	//댓글 작성
	public int create(ReplyVO vo) throws Exception;
	//댓글 상세조회
	public ReplyVO read(int rno) throws Exception;
	//댓글 수정
	public int update(ReplyVO vo) throws Exception;
	//댓글 삭제
	public int delete(ReplyVO vo) throws Exception;
	//댓글 목록
    public List<ReplyVO> list(int bno, String writerId, Criteria cri) throws Exception;
    //댓글 카운트
	public int count(int bno);
	//댓글 존재 체크
	public boolean check(int bno) throws Exception;
	

}
