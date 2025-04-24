package com.chilluminati.chillstock.admin.user.repository;

import com.chilluminati.chillstock.admin.user.dto.UserBizDTO;
import com.chilluminati.chillstock.admin.user.vo.BizVO;
import com.chilluminati.chillstock.admin.user.vo.UserBizBackupVO;
import com.chilluminati.chillstock.admin.user.vo.UserBizVO;
import com.chilluminati.chillstock.admin.user.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AdminUserRepo {


   /**
    * 모든 회원 불러오기
    * @param limit
    * @param offset
    * @return
    */
   List<UserBizVO> getAllUsersBiz(@Param("limit") int limit, @Param("offset") int offset);

   /**
    * 전체 회원 수 반환
    * @return
    */
   int countAllUsers();

   /**
    * 삭제된 전체 회원 수
    * @return
    */
   int countAllDeleted();

   /**
    * 이름으로 계정 검색하기
    * @param userName
    * @return
    */
   List<UserBizVO> getUsersByName(String userName);


   /**
    * 회원 계정 정보 상세 조회하기
    * @param userId
    * @return
    */
   UserBizVO getUserBizById(Integer userId);

   /**
    * 회원 계정 한개/여러개 삭제하기
    * @param userIds
    * @return
    */
   int deleteUsersByIds(List<Integer> userIds);


   /**
    * 대기중인 회원 계정 한개/여러개 승인하기
    * @param userIds
    * @return
    */
   int approveUsersByIds(List<Integer> userIds);

   /**
    * 삭제된 회원 백업 테이블 회원계정 모두 불러오기
    * @return
    */
   List<UserBizBackupVO> findAllDeletedUsers();


   /**
    * 회원 삭제전에 백업 테이블에 저장하기
    * @param userIds
    */
   void backupUsersBeforeDelete(List<Integer> userIds);
}
