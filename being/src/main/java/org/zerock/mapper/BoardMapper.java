package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;

public interface BoardMapper {
	List<BoardVO> selectBoardList() throws Exception;
}