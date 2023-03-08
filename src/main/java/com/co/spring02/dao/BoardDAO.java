package com.co.spring02.dao;

import java.util.List;

import com.co.spring02.vo.BoardVO;

public interface BoardDAO {
	
	//1.게시글 작성
	public void create(BoardVO vo) throws Exception;
	//2.게시글 상세조회
	public BoardVO read(int bno) throws Exception;
	//3.게시글 수정
	public void update(BoardVO vo) throws Exception;
	//4.게시글 삭제
	public void delete(int bno) throws Exception;
	//5.게시글 목록
    public List<BoardVO> list() throws Exception;
	//6. 게시글 조회수 증가
    public void increaseViewcnt(int bno) throws Exception;


}