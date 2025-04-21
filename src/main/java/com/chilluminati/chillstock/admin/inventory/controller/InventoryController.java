package com.chilluminati.chillstock.admin.inventory.controller;

import com.chilluminati.chillstock.admin.inventory.dto.InventoryHistoryDTO;
import com.chilluminati.chillstock.admin.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("admin/inventory")
    public String getInventoryHistory(Model model) {
        List<InventoryHistoryDTO> historyList = inventoryService.getInventoryHistoryList();
        model.addAttribute("historyList", historyList);
        return "admin/inventory";  // 타임리프 템플릿 경로
    }

}
