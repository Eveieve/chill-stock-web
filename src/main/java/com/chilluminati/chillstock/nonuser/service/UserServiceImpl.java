package com.chilluminati.chillstock.nonuser.service;

import com.chilluminati.chillstock.nonuser.dto.SignUpDTO;
import com.chilluminati.chillstock.nonuser.repository.BizRepo;
import com.chilluminati.chillstock.nonuser.repository.UserRepo;
import com.chilluminati.chillstock.nonuser.vo.BizVO;
import com.chilluminati.chillstock.nonuser.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final BizRepo bizRepo;
    private final ModelMapper modelMapper;

    /**
     * 회원가입
     * @param signupDto 회원가입 요청 데이터
     */
    @Override
    @Transactional
    public void signUp(SignUpDTO signupDto) {
        // DTO → UserVO 변환
        UserVO userVo = modelMapper.map(signupDto, UserVO.class);

        // 유저 저장 (user_id 생성됨)
        userRepo.insertUser(userVo); // userId 자동 생성됨

        // builder 기반 BizVO 생성
        BizVO bizVo = BizVO.builder()
                .businessRegistNum(signupDto.getBizNumber())
                .businessName(signupDto.getBizName())
                .businessAddress(signupDto.getBizAddress())
                .userId(userVo.getUserId())
                .build();

        bizRepo.insertBiz(bizVo);

        // 사업체 저장
        bizRepo.insertBiz(bizVo);
    }
}