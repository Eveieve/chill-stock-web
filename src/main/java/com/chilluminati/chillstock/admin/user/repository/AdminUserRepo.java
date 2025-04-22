package com.chilluminati.chillstock.admin.user.repository;

import com.chilluminati.chillstock.admin.user.dto.UserBizDTO;
import com.chilluminati.chillstock.admin.user.vo.BizVO;
import com.chilluminati.chillstock.admin.user.vo.UserBizBackupVO;
import com.chilluminati.chillstock.admin.user.vo.UserBizVO;
import com.chilluminati.chillstock.admin.user.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AdminUserRepo {

   // 이름으로 회원 정보 검색하기
   List<UserBizVO> getUsersByName(String userName);

   // id로 회원정보만 조회하기
   UserVO getUserById(Integer userId);

   // userid로 사업체 번호만 조회하기
   BizVO getBizById(Integer userId);

   // 테이블 기본키로 회원 찾기 (회원 상세조회하기 클릭)
   UserBizVO getUserBizById(Integer userId);

   // 로그인 아이디 로 회원의 모든 정보  불러오기
   UserVO findByLoginId(String loginId);

   /**
    * email로 회원의 모든 정보 (비밀번호 포함) 불러오기
    * @param email
    * @return
    */
   UserVO findByEmail(String email);

   /**
    * 사용자로부터 새 비밀번호를 받아 비밀번호를 재설정하기
    * @param newPassword
    */
   void updatePassword(@Param("newPassword") String newPassword, @Param(("userLoginId")) String userLoginId);

   /**
    * 이메일로 아이디 찾기
    * @param email
    */
   String findLoginId(@Param("email") String email);

   int deleteUserById(Integer userId);

   /**
    * 회원 계정 한개/여러개 삭제하기
    * @param userIds
    * @return
    */
   int deleteUsersByIds(List<Integer> userIds);

   int approveUserById(Integer userId);

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
    * 모든 회원 계정 불러오기
    * @param limit
    * @param offset
    * @return
    */
   List<UserBizVO> getAllUsersWithBiz(@Param("limit") int limit, @Param("offset") int offset);
}
