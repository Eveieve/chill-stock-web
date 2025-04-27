package com.chilluminati.chillstock.nonuser.controller;

import com.chilluminati.chillstock.nonuser.dto.EmailDupDTO;
import com.chilluminati.chillstock.nonuser.service.NonUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NonUserApiController {

    private final NonUserService nonUserService;

    @PostMapping("/nonuser/api/signup/check-email")
    public ResponseEntity<Map<String, Boolean>> checkEmailDuplicate(@RequestBody EmailDupDTO emailDupDto) {
        log.info(">>>>>>>>>>>>>>>> API 컨트롤러 호출됨");
        log.debug(">>>>>>>>>>>>>>>> API 컨트롤러 호출됨");
        boolean isDuplicate = nonUserService.checkEmailDuplicate(emailDupDto);
        Map<String, Boolean> result = new HashMap<>();
        result.put("duplicate", isDuplicate);

        return ResponseEntity
                .ok()
                .header("Content-Type", "application/json")
                .body(result);
    }
}
