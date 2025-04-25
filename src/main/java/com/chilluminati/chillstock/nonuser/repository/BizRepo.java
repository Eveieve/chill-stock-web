package com.chilluminati.chillstock.nonuser.repository;

import com.chilluminati.chillstock.nonuser.vo.BizVO;
import org.apache.ibatis.annotations.Mapper;


public interface BizRepo {
    /**
     * 사업체 정보 디비 저장하기
     * @param bizVo
     */
    void insertBiz(BizVO bizVo);

    /**
     * 사업체 정보 기본키로 조회하기
     * @param userId
     * @return
     */
    BizVO findByUserId(Integer userId);



}
