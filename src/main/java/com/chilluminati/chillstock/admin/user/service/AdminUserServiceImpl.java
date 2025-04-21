package com.chilluminati.chillstock.admin.user.service;

import com.chilluminati.chillstock.admin.user.exception.AdminUserErrorCode;
import com.chilluminati.chillstock.admin.user.exception.AdminUserException;
import com.chilluminati.chillstock.admin.user.repository.AdminUserRepo;
import com.chilluminati.chillstock.admin.user.vo.UserBizBackupVO;
import com.chilluminati.chillstock.admin.user.vo.UserVO;
import com.chilluminati.chillstock.nonuser.exception.UserNotFoundException;
import com.chilluminati.chillstock.nonuser.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserRepo adminUserRepo;

    @Override
    public UserVO findUserById(Integer userId) {
        UserVO user = adminUserRepo.findByUserId(userId);
        if (user == null) {
            throw new UserNotFoundException("해당 ID의 회원을 찾을 수 없습니다. ID: " + userId);
        }
        return user;
    }
    @Override
    public void deleteUserById(Integer userId) {
        int updatedRows = adminUserRepo.deleteUserById(userId);
        if (updatedRows == 0) {
            throw new AdminUserException(AdminUserErrorCode.DELETE_FAILED);
        }
    }

    @Override
    public void deleteUsersByIds(List<Integer> userIds) {
        int updatedRows = adminUserRepo.deleteUsersByIds(userIds);
        if (updatedRows == 0) {
            throw new AdminUserException(AdminUserErrorCode.DELETE_FAILED);
        }
    }

    @Override
    public void approveUserById(Integer userId) {
        int updatedRows = adminUserRepo.approveUserById(userId);
        if (updatedRows == 0) {
            throw new AdminUserException(AdminUserErrorCode.APPROVAL_FAILED);
        }
    }

    @Override
    public void approveUsersByIds(List<Integer> userIds) {
        int updatedRows = adminUserRepo.approveUsersByIds(userIds);
        if (updatedRows == 0) {
            throw new AdminUserException(AdminUserErrorCode.APPROVAL_FAILED);
        }
    }
//    @Override
//    public List<UserDTO> getDeletedUsers() {
//        return List.of();
//    }

    @Override
    public List<UserBizBackupVO> getAllDeletedUsers() {
        return adminUserRepo.findAllDeletedUsers();
    }
}
