package com.co.spring02.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.co.spring02.dao.BoardDAO;
import com.co.spring02.vo.BoardVO;
import com.co.spring02.vo.Criteria;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject BoardDAO boardDao;
	
	@Override
	public void create(BoardVO vo) throws Exception {
		boardDao.create(vo);
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		BoardVO vo = boardDao.read(bno);
		List<Integer> attachList = boardDao.selectFileNoList(bno);
		if(attachList == null) {
			attachList = new ArrayList<Integer>();
		}
		vo.setAttachList(attachList);
		return vo;
	}

	@Override
	public int update(BoardVO vo) throws Exception {
		return boardDao.update(vo);
		
	}

	@Override
	public int delete(BoardVO vo) throws Exception {
		return boardDao.delete(vo);
		
	}

	@Override
	public void increaseViewcnt(int bno) throws Exception {
		boardDao.increaseViewcnt(bno);
		
	}

	@Override
	public List<BoardVO> list(String searchOption, String keyword,Criteria cri) throws Exception {
		return boardDao.list(searchOption,keyword,cri);
	}

	@Override
	public int countArticle(String searchOption, String keyword) {
		return boardDao.countArticle(searchOption,keyword); 
	}

	@Override
	public boolean checkArticle(int bno) throws Exception {
		return boardDao.checkArticle(bno); 
	}
	
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception {
		return boardDao.selectFileList(bno);
	}

	
	//@Override
	//public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
	//	return boardDao.selectFileInfo(map);
	//}

	@Override
	public int insertFile(Map<String, Object> map) throws Exception {
		return boardDao.insertFile(map);
	}

	@Override
	public void deleteFile(int bno, int fileNo) throws Exception {
		boardDao.deleteFile(bno,fileNo);
		
	}

	//개선필요. 루프대신 update where in 구문을 써야 됨
	@Override
	public void updateBnoToFiles(int bno, List<Integer> attachList) throws Exception {
		for (int attach : attachList) {
			boardDao.FileBnoSet(bno, attach);
		}
	}
	
	//개선필요. 루프대신 delete where in 구문을 써야 됨
	@Override
	public void DeleteFiles(int bno, List<Integer> attachList) throws Exception {
		for (int attach : attachList) {
			boardDao.deleteFile(bno, attach);
		}
	}


}
