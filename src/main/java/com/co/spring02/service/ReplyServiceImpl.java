package com.co.spring02.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.co.spring02.dao.ReplyDAO;
import com.co.spring02.vo.Criteria;
import com.co.spring02.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements  ReplyService{

	@Inject ReplyDAO replyDao;
	@Override
	public int create(ReplyVO vo) throws Exception {
		return replyDao.create(vo);
	}

	@Override
	public ReplyVO read(int rno) throws Exception {
		return replyDao.read(rno);
	}

	@Override
	public int update(ReplyVO vo) throws Exception {
		return replyDao.update(vo);

	}

	@Override
	public int delete(ReplyVO vo) throws Exception {
		return replyDao.delete(vo);
	}

	@Override
	public List<ReplyVO> list(int bno, String writerId, Criteria cri) throws Exception {
		return replyDao.list(bno,writerId,cri);
	}

	@Override
	public int count(int bno) {
		return replyDao.count(bno);
	}

	@Override
	public boolean check(int bno) throws Exception {
		return replyDao.check(bno);
	}

}
