package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.AttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;
import org.zerock.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	@Transactional
	public void create(BoardVO board) throws Exception {
		boardMapper.create(board);
	}

	@Override
	@Transactional
	public List<BoardVO> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return boardMapper.listPage(page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return boardMapper.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		return boardMapper.countPaging(cri);
	}

	@Override
	@Transactional
	public BoardVO read(Integer num) throws Exception {
		return boardMapper.read(num);
	}

	@Override
	public void modify(BoardVO board) throws Exception {
		boardMapper.update(board);
	}

	@Override
	public void remove(Integer num) throws Exception {
		boardMapper.delete(num);
	}

	// 업로드 테스트

	@Transactional
	@Override
	public void addAttach(AttachVO vo) throws Exception {

		boardMapper.addAttach(vo);

		String[] files = vo.getFiles();

		if (files == null) {
			return;
		}

		for (String fileName : files) {
			boardMapper.addAttach(fileName);
		}
	}

	//
	// @Override
	// public void regist(BoardVO board) throws Exception {
//			      dao.create(board);
	// }

	// @Override
	// public BoardVO read(Integer bno) throws Exception {
//			      return dao.read(bno);
	// }

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public AttachVO readA(Integer bno) throws Exception {
		boardMapper.updateViewCnt(bno);
		return boardMapper.read(bno);
	}

	// @Override
	// public void modify(BoardVO board) throws Exception {
//			      dao.update(board);
	// }

	@Transactional
	@Override
	public void modifyA(AttachVO attach) throws Exception {
		boardMapper.update(attach);

		Integer bno = attach.getBno();

		boardMapper.deleteAttach(bno);

		String[] files = attach.getFiles();

		if (files == null) {
			return;
		}

		for (String fileName : files) {
			boardMapper.replaceAttach(fileName, bno);
		}
	}

	// @Override
	// public void remove(Integer bno) throws Exception {
//			      dao.delete(bno);
	// }

	@Transactional
	@Override
	public void removeA(Integer bno) throws Exception {
		boardMapper.deleteAttach(bno);
		boardMapper.delete(bno);
	}

}
