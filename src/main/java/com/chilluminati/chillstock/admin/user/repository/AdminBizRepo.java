package com.chilluminati.chillstock.admin.user.repository;

import com.chilluminati.chillstock.nonuser.vo.BizVO;


public interface AdminBizRepo {
    void insertBiz(BizVO bizVo);

    BizVO findByUserId(Integer userId);

    BizVO findByBusinessRegistNum(String businessRegistNum);

}
