package org.zerock.service;

import java.util.List;

import org.zerock.domain.AttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {
	
	public void create(BoardVO board) throws Exception;
	
	public List<BoardVO> selectBoardList() throws Exception;
	
	 public BoardVO read(Integer num) throws Exception;
	 
	 public List<BoardVO> listPage(int page) throws Exception;

	 public List<BoardVO> listCriteria(Criteria cri) throws Exception;

	 public int listCountCriteria(Criteria cri) throws Exception;
	 
	 public void modify(BoardVO board) throws Exception;

	 public void remove(Integer num) throws Exception;
	 
	 // 업로드 테스트
	 
	 public void registA(AttachVO attach) throws Exception;

	  public AttachVO readA(Integer bno) throws Exception;

	  public void modifyA(AttachVO attach) throws Exception;

	  public void removeA(Integer bno) throws Exception;


}
