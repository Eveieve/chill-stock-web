package com.chilluminati.chillstock.nonuser.repository;

import com.chilluminati.chillstock.nonuser.vo.BizVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BizRepo {
    void insertBiz(BizVO bizVo);

    BizVO findByUserId(Integer userId);

}
