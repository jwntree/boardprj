package com.co.spring02.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.co.spring02.vo.BoardVO;
import com.co.spring02.vo.Criteria;

public interface BoardDAO {
    
	//1.게시글 작성
	public void create(BoardVO vo) throws Exception;
	//2.게시글 상세조회
	public BoardVO read(int bno) throws Exception;
	//3.게시글 수정
	public int update(BoardVO vo) throws Exception;
	//4.게시글 삭제
	public int delete(BoardVO vo) throws Exception;
	//5.게시글 목록
    public List<BoardVO> list(String searchOption, String keyword,Criteria cri) throws Exception;
	//6. 게시글 조회수 증가
    public void increaseViewcnt(int bno) throws Exception;
    //7. 게시글 카운트
	public int countArticle(String searchOption, String keyword);
	//8.게시글 존재 체크
	public boolean checkArticle(int bno) throws Exception;
	
	//첨부파일 업로드
	public int insertFile(Map<String, Object> map) throws Exception;
	//첨부파일 조회
	public List<Map<String, Object>> selectFileList(int bno) throws Exception;
	//첨푸파일 다운로드
	public Map<String,Object> selectFileInfo(Map<String,Object> map) throws Exception;
	//첨부파일 삭제
	public void deleteFile(Map<String,Object> map) throws Exception;
	//첨부파일 bno 설정
	public void FileBnoSet(int bno, int fileNo) throws Exception;

}
