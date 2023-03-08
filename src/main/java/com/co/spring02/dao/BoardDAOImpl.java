package com.co.spring02.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.co.spring02.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Inject
	SqlSession sqlSession;
	
	@Override
	public void create(BoardVO vo) throws Exception {
        sqlSession.insert("boardMapper.insert",vo);
	}

	@Override
	public BoardVO read(int bno) throws Exception {
        return sqlSession.selectOne("boardMapper.view",bno);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		sqlSession.update("boardMapper.updateArticle",vo);
		
	}

	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("boardMapper.deleteArticle",bno);
		
	}

	@Override
	public List<BoardVO> list() throws Exception {
        return sqlSession.selectList("boardMapper.list");

	}

	@Override
	public void increaseViewcnt(int bno) throws Exception {
		sqlSession.update("boardMapper.increaseViewCnt",bno);
		
	}

}
