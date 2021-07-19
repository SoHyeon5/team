package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.StoreReviewVO;

public interface StoreReviewService {

  public void addReview(StoreReviewVO vo) throws Exception;

  public List<StoreReviewVO> listReview(Integer bno) throws Exception;

  public void modifyReview(StoreReviewVO vo) throws Exception;

  public void removeReview(Integer rno) throws Exception;

  public List<StoreReviewVO> listReviewPage(Integer bno, Criteria cri) 
      throws Exception;

  public int count(Integer bno) throws Exception;
}
