package com.chilluminati.chillstock.admin.warehouse.controller;


import com.chilluminati.chillstock.admin.warehouse.dto.AdminWarehouseDto;
import com.chilluminati.chillstock.admin.warehouse.service.AdminWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor

public class AdminWarehouseController {

    private final AdminWarehouseService adminWarehouseService;

    @GetMapping("admin/warehouse")
    public String adminWarehouse(Model model) {
        model.addAttribute("warehouseList", adminWarehouseService.getAllWarehouses());
        return "admin/warehouse";
    }

    @PostMapping("/admin/warehouse/register")
    public String registerWarehouse(@ModelAttribute AdminWarehouseDto adminWarehouseDto) {
        adminWarehouseService.registerWarehouse(adminWarehouseDto);
        return "redirect:/admin/warehouse";
    }

}
