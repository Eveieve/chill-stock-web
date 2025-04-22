package com.chilluminati.chillstock.admin.user.service;

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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserRepo adminUserRepo;

    private final ModelMapper modelMapper;

    /**
     * 클릭하여 회원 한명 상세 정보 조회하기
     * @param userId
     * @return
     */
    @Override
    public UserBizVO viewUserDetail(Integer userId) {

        UserBizVO userBizVo = adminUserRepo.getUserBizById(userId);
        return userBizVo;


    }

    /**
     * 회원 이름으로 회원계정 불러오기
     * @param name
     * @return
     */
    @Override
    public List<UserBizDTO> searchUserByName(String name) {
        List<UserBizVO> userBizList = adminUserRepo.getUsersByName(name);

        // ModelMapper를 이용한 변환
        return userBizList.stream()
                .map(vo -> modelMapper.map(vo, UserBizDTO.class))
                .collect(Collectors.toList());
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

    /**
     * 탈퇴/삭제된 회원 모두 조회하기
     * @return
     */
    @Override
    public List<UserBizBackupVO> getAllDeletedUsers() {
        return adminUserRepo.findAllDeletedUsers();
    }
}
