package org.zerock.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
import org.zerock.domain.SearchCriteria;
import org.zerock.service.BoardService;
import org.zerock.util.MediaUtils;
import org.zerock.util.UploadFileUtils;

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
	    
	    logger.info(board.toString());
	    
	    return "redirect:/board/listArticle";
	  }

//	@RequestMapping(value = "/listArticle")
//	public String boardList(Model model) throws Exception {
//		logger.info("// /board/listArticle");
//
//		List<BoardVO> listArticle = boardService.selectBoardList();
//
//		logger.info("// listArticle.toString()=" + listArticle.toString());
//
//		model.addAttribute("listArticle", listArticle);
//		
//		return "/board/listArticle";
//		
//	}
	  
	  @RequestMapping(value = "/listArticle", method = RequestMethod.GET)
	  public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

	    logger.info(cri.toString());

	     model.addAttribute("listArticle", boardService.listCriteria(cri));
//	    model.addAttribute("listArticle", boardService.listPage(page));

	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setCri(cri);

	     pageMaker.setTotalCount(boardService.listCountCriteria(cri));
//	    pageMaker.setTotalCount(boardService.listSearchCount(cri));

	    model.addAttribute("pageMaker", pageMaker);
	  }
	
//	 @RequestMapping(value = "/readArticle", method = RequestMethod.GET)
//	  public void read(@RequestParam("num") int num, Model model) throws Exception {
//
//	    model.addAttribute(boardService.read(num));
//	  }
	 
	  @RequestMapping(value = "/readArticle", method = RequestMethod.GET)
	     public void read(@RequestParam("num") int num, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {

	       model.addAttribute(boardService.read(num));
	     }
	   
	     @RequestMapping(value = "/remove", method = {RequestMethod.POST ,RequestMethod.GET})
	     public String remove(@RequestParam("num") int num, RedirectAttributes rttr) throws Exception {

	        boardService.remove(num);

	       rttr.addFlashAttribute("msg", "SUCCESS");

	       return "redirect:/board/listArticle";
	     }

	     @RequestMapping(value = "/modifyForm", method = RequestMethod.GET)
	     public void modifyGET(int num, Model model) throws Exception {

	       model.addAttribute(boardService.read(num));
	     }

	     @RequestMapping(value = "/modifyForm", method = RequestMethod.POST)
	     public String modifyPOST(BoardVO board, RedirectAttributes rttr) throws Exception {

	       logger.info("mod post............");

	       boardService.modify(board);
	       rttr.addFlashAttribute("msg", "SUCCESS");

	       return "redirect:/board/listArticle";
	     } 
	     
//	     --업로드 테스트

	     @Resource(name = "uploadPath")
	     private String uploadPath;

	     @RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	     public void uploadForm() {
	     }

	     @RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
	     public String uploadForm(MultipartFile file, Model model) throws Exception {

	       logger.info("originalName: " + file.getOriginalFilename());
	       logger.info("size: " + file.getSize());
	       logger.info("contentType: " + file.getContentType());

	       String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());

	       model.addAttribute("savedName", savedName);

	       return "uploadResult";
	     }

	     @RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
	     public void uploadAjax() {
	     }

	     private String uploadFile(String originalName, byte[] fileData) throws Exception {

	       UUID uid = UUID.randomUUID();

	       String savedName = uid.toString() + "_" + originalName;

	       File target = new File(uploadPath, savedName);

	       FileCopyUtils.copy(fileData, target);

	       return savedName;

	     }
	     
	     @ResponseBody
	     @RequestMapping(value ="/uploadAjax", method=RequestMethod.POST, 
	                     produces = "text/plain;charset=UTF-8")
	     public ResponseEntity<String> uploadAjax(MultipartFile file)throws Exception{
	       
	       logger.info("originalName: " + file.getOriginalFilename());
	       
	      
	       return 
	         new ResponseEntity<>(
	             UploadFileUtils.uploadFile(uploadPath, 
	                   file.getOriginalFilename(), 
	                   file.getBytes()), 
	             HttpStatus.CREATED);
	     }
	     
	     
	     @ResponseBody
	     @RequestMapping("/displayFile")
	     public ResponseEntity<byte[]>  displayFile(String fileName)throws Exception{
	       
	       InputStream in = null; 
	       ResponseEntity<byte[]> entity = null;
	       
	       logger.info("FILE NAME: " + fileName);
	       
	       try{
	         
	         String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
	         
	         MediaType mType = MediaUtils.getMediaType(formatName);
	         
	         HttpHeaders headers = new HttpHeaders();
	         
	         in = new FileInputStream(uploadPath+fileName);
	         
	         if(mType != null){
	           headers.setContentType(mType);
	         }else{
	           
	           fileName = fileName.substring(fileName.indexOf("_")+1);       
	           headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	           headers.add("Content-Disposition", "attachment; filename=\""+ 
	             new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
	         }

	           entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), 
	             headers, 
	             HttpStatus.CREATED);
	       }catch(Exception e){
	         e.printStackTrace();
	         entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
	       }finally{
	         in.close();
	       }
	         return entity;    
	     }
	       
	     @ResponseBody
	     @RequestMapping(value="/deleteFile", method=RequestMethod.POST)
	     public ResponseEntity<String> deleteFile(String fileName){
	       
	       logger.info("delete file: "+ fileName);
	       
	       String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
	       
	       MediaType mType = MediaUtils.getMediaType(formatName);
	       
	       if(mType != null){      
	         
	         String front = fileName.substring(0,12);
	         String end = fileName.substring(14);
	         new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
	       }
	       
	       new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
	       
	       
	       return new ResponseEntity<String>("deleted", HttpStatus.OK);
	     }  
	     
	     @ResponseBody
	     @RequestMapping(value="/deleteAllFiles", method=RequestMethod.POST)
	     public ResponseEntity<String> deleteFile(@RequestParam("files[]") String[] files){
	       
	       logger.info("delete all files: "+ files);
	       
	       if(files == null || files.length == 0) {
	         return new ResponseEntity<String>("deleted", HttpStatus.OK);
	       }
	       
	       for (String fileName : files) {
	         String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
	         
	         MediaType mType = MediaUtils.getMediaType(formatName);
	         
	         if(mType != null){      
	           
	           String front = fileName.substring(0,12);
	           String end = fileName.substring(14);
	           new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
	         }
	         
	         new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
	         
	       }
	       return new ResponseEntity<String>("deleted", HttpStatus.OK);
	     }  
}
