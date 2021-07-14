package org.zerock.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private BoardService boardService;
	
	 @RequestMapping(value = "/newArticleForm", method = RequestMethod.GET)
	  public void registerGET(BoardVO board, Model model) throws Exception {

	    logger.info("newArticleForm get ...........");
	  }

	  // @RequestMapping(value = "/register", method = RequestMethod.POST)
	  // public String registPOST(BoardVO board, Model model) throws Exception {
	  //
	  // logger.info("regist post ...........");
	  // logger.info(board.toString());
	  //
	  // service.regist(board);
	  //
	  // model.addAttribute("result", "success");
	  //
	  // //return "/board/success";
	  // return "redirect:/board/listAll";
	  // }

	  @RequestMapping(value = "/newArticleForm", method = RequestMethod.POST)
	  public String registPOST(BoardVO board, RedirectAttributes rttr) throws Exception {

	    logger.info("newArticleForm post ...........");
	    logger.info(board.toString());

	    boardService.create(board);

	    rttr.addFlashAttribute("msg", "SUCCESS");
	    
	    return "redirect:/board/newArticleForm";
	  }

	@RequestMapping(value = "/listArticle")
	public String boardList(Model model) throws Exception {
		logger.info("// /board/listArticle");

		List<BoardVO> listArticle = boardService.selectBoardList();

		logger.info("// listArticle.toString()=" + listArticle.toString());

		model.addAttribute("listArticle", listArticle);
		
		return "/board/listArticle";
		
	}
	
	
}
