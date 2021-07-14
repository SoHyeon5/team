package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;

public interface BoardMapper {
	
	void create(BoardVO board) throws Exception;
	
	List<BoardVO> selectBoardList() throws Exception;
	
	public BoardVO read(Integer bno) throws Exception;
}
