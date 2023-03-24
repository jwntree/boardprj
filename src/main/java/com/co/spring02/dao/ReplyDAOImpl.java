package com.co.spring02.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.co.spring02.vo.Criteria;
import com.co.spring02.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Inject SqlSession sqlSession;

	@Override
	public int create(ReplyVO vo) throws Exception {
		return sqlSession.insert("boardMapper.insertReply",vo);
	}

	@Override
	public ReplyVO read(int rno) throws Exception {
		return sqlSession.selectOne("boardMapper.viewReply",rno);
	}

	@Override
	public int update(ReplyVO vo) throws Exception {
		return sqlSession.update("boardMapper.updateReply",vo);

	}

	@Override
	public int delete(ReplyVO vo) throws Exception {
		return sqlSession.update("boardMapper.deleteReply",vo);

	}

	@Override
	public List<ReplyVO> list(int bno, String writerId, Criteria cri) throws Exception {
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("bno", bno);
	    map.put("start", cri.getRowStart());
	    map.put("end", cri.getRowEnd());
	    map.put("writerId", writerId);
		return sqlSession.selectList("boardMapper.selectReply",map);
	}

	@Override
	public int count(int bno) {
		return sqlSession.selectOne("boardMapper.countReply",bno);
	}

	@Override
	public boolean check(int bno) throws Exception {
		return sqlSession.selectOne("boardMapper.checkReply",bno);
	}


}
