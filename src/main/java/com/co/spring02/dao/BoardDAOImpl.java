package com.co.spring02.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public int update(BoardVO vo) throws Exception {
		return sqlSession.update("boardMapper.updateArticle",vo);
		
	}

	@Override
	public int delete(BoardVO vo) throws Exception {
		return sqlSession.delete("boardMapper.deleteArticle",vo);
		
	}

	@Override
	public List<BoardVO> list(String searchOption, String keyword,int start, int end) throws Exception {
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("searchOption", searchOption);
	    map.put("keyword", keyword);
	    map.put("start", start);
	    map.put("end", end);
		return sqlSession.selectList("boardMapper.list", map);
	}
	
	@Override
	public int countArticle(String searchOption, String keyword) {
	    // 검색옵션, 키워드 맵에 저장
	    Map<String, String> map = new HashMap<String, String>();
	    map.put("searchOption", searchOption);
	    map.put("keyword", keyword);
	    return sqlSession.selectOne("boardMapper.countArticle", map);
	}


	@Override
	public void increaseViewcnt(int bno) throws Exception {
		sqlSession.update("boardMapper.increaseViewCnt",bno);
		
	}
}
