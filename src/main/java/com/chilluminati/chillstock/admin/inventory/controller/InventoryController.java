package com.chilluminati.chillstock.admin.inventory.controller;

import com.chilluminati.chillstock.admin.inventory.dto.InventoryHistoryDTO;
import com.chilluminati.chillstock.admin.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    //  전체 페이지 진입 시 (최초 렌더링)
    @GetMapping("/admin/inventory")
    public String getInventoryHistory(@RequestParam(defaultValue = "1") int page,
                                      Model model) {

        int pageSize = 10;

        List<InventoryHistoryDTO> historyList = inventoryService.getInventoryHistoryPaged(page, pageSize);
        int totalCount = inventoryService.getTotalHistoryCount();
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);

        if (page > totalPages) page = totalPages;
        if (page < 1) page = 1;
        model.addAttribute("historyList", historyList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "admin/inventory"; // 타임리프 전체 페이지
    }

    // fragment만 요청할 경우 (AJAX 전용)
    @GetMapping("/admin/inventory/fragment")
    public String getInventoryHistoryFragment(@RequestParam(defaultValue = "1") int page,
                                              Model model) {
        int pageSize = 10;

        List<InventoryHistoryDTO> historyList = inventoryService.getInventoryHistoryPaged(page, pageSize);
        model.addAttribute("historyList", historyList);

        return "admin/inventory :: listFragment"; // 테이블 fragment만 렌더링
    }

}
