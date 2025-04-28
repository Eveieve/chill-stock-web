package com.chilluminati.chillstock.admin.warehouse.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OverRemainSpaceException.class)
    public String handleOverRemainSpaceException(OverRemainSpaceException ex,
                                                 RedirectAttributes redirectAttributes,
                                                 HttpServletRequest request) {
        log.error("OverRemainSpaceException 발생: {}", ex.getMessage());

        // 💡 request 파라미터에서 warehouseId 꺼내서 리다이렉트에 추가
        String warehouseId = request.getParameter("warehouseId");
        // 에러 메시지 저장
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        redirectAttributes.addFlashAttribute("warehouseId", warehouseId);


        return "redirect:/admin/warehouse/detail?warehouseId=" + warehouseId;
    }
}
