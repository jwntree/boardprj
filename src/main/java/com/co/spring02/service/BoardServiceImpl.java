package com.co.spring02.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.co.spring02.dao.BoardDAO;
import com.co.spring02.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject BoardDAO boardDao;
	
	@Override
	public void create(BoardVO vo) throws Exception {
		boardDao.create(vo);
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return boardDao.read(bno);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		boardDao.update(vo);
		
	}

	@Override
	public void delete(int bno) throws Exception {
		boardDao.delete(bno);
		
	}

	@Override
	public void increaseViewcnt(int bno) throws Exception {
		boardDao.increaseViewcnt(bno);
		
	}

	@Override
	public List<BoardVO> list(String searchOption, String keyword,int start, int end) throws Exception {
		return boardDao.list(searchOption,keyword,start, end);
	}

	@Override
	public int countArticle(String searchOption, String keyword) {
		return boardDao.countArticle(searchOption,keyword); 
	}

}
