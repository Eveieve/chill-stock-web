package com.chilluminati.chillstock.admin.user.service;

import com.chilluminati.chillstock.admin.user.common.UserStatus;
import com.chilluminati.chillstock.admin.user.dto.DeletedUserDTO;
import com.chilluminati.chillstock.admin.user.dto.UserBizDTO;
import com.chilluminati.chillstock.admin.user.exception.AdminUserErrorCode;
import com.chilluminati.chillstock.admin.user.exception.AdminUserException;
import com.chilluminati.chillstock.admin.user.repository.AdminUserRepo;
import com.chilluminati.chillstock.admin.user.vo.BizVO;
import com.chilluminati.chillstock.admin.user.vo.UserBizBackupVO;
import com.chilluminati.chillstock.admin.user.vo.UserBizVO;
import com.chilluminati.chillstock.admin.user.vo.UserVO;
import com.chilluminati.chillstock.nonuser.exception.UserNotFoundException;
import com.chilluminati.chillstock.nonuser.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserRepo adminUserRepo;

    private final ModelMapper modelMapper;

//    public int getTotalPages() {
//        int totalCount = adminUserRepo.countAllUsers(); // 단순 COUNT(*)
//        int size = 10; // 고정값
//        return (int) Math.ceil((double) totalCount / size);
//    }

    @Override
    public int countAllUsers() {
        return adminUserRepo.countAllUsers();
    }

    @Override
    public int countAllDeleted() {
        return adminUserRepo.countAllDeleted();
    }

    @Override
    public List<UserBizDTO> getAllUsersByPage(int page) {
        // 화면에 10개씩 출력
        final int size = 10;
        int safePage = Math.max(page, 1); // 0 이하 방지
        int offset = (safePage - 1) * size;

        List<UserBizVO> voList = adminUserRepo.getAllUsersBiz(size, offset);
        // ModelMapper를 이용한 변환
        return voList.stream()
                .map(vo -> modelMapper.map(vo, UserBizDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<UserBizDTO> getPendingUsersByPage(int page) {
        final int size = 10;
        int safePage = Math.max(page, 1);
        int offset = (safePage - 1) * size;

        List<UserBizVO> allUsers = adminUserRepo.getAllUsersBiz(size, offset);

        // 상태가 pending인 회원만 필터링
        return allUsers.stream()
                .filter(user -> "pending".equalsIgnoreCase(user.getUserStatus()))
                .map(vo -> modelMapper.map(vo, UserBizDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public UserBizDTO viewUserDetail(Integer userId) {

        UserBizVO userBizVo = adminUserRepo.getUserBizById(userId);
        // ModelMapper를 이용한 변환
        return modelMapper.map(userBizVo, UserBizDTO.class);

    }

    @Override
    public List<UserBizDTO> searchUserByName(String name) {
        List<UserBizVO> userBizList = adminUserRepo.getUsersByName(name);

        // ModelMapper를 이용한 변환
        return userBizList.stream()
                .map(vo -> modelMapper.map(vo, UserBizDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteUsersByIds(List<Integer> userIds) {
        // 백업하기
        adminUserRepo.backupUsersBeforeDelete(userIds);

        // 삭제하기
        int updatedRows = adminUserRepo.deleteUsersByIds(userIds);
        if (updatedRows == 0) { // 삭제 실패시 메시지
            throw new AdminUserException(AdminUserErrorCode.DELETE_FAILED);
        }
    }


    @Override
    public void approveUsersByIds(List<Integer> userIds) {
        int updatedRows = adminUserRepo.approveUsersByIds(userIds);
        if (updatedRows == 0) { // 승인에 실패하면
            // 예외 던지기
            throw new AdminUserException(AdminUserErrorCode.APPROVAL_FAILED);
        }
    }


    @Override
    public List<DeletedUserDTO> getAllDeletedByPage(@Param("limit") int limit) {


        List<UserBizBackupVO> userList = adminUserRepo.findAllDeletedUsers();

       return  userList.stream()
                .map(vo -> modelMapper.map(vo, DeletedUserDTO.class))
                .collect(Collectors.toList());
    }
}
