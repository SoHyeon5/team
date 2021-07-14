package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;

public interface BoardService {
	
	public void create(BoardVO board) throws Exception;
	
	List<BoardVO> selectBoardList() throws Exception;
	
	 public BoardVO read(Integer num) throws Exception;
}
