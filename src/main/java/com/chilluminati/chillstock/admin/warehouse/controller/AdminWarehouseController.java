package com.chilluminati.chillstock.admin.warehouse.controller;


import com.chilluminati.chillstock.admin.warehouse.dto.AdminWarehouseDto;
import com.chilluminati.chillstock.admin.warehouse.service.AdminWarehouseService;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminWarehouseVo;
import com.chilluminati.chillstock.common.ResultList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/admin/warehouse/search-address")
    public String searchAddress(@RequestParam String address, Model model) {
        ResultList<List<AdminWarehouseVo>> warehouses = adminWarehouseService.getAllWarehousesByAddress(address);
        model.addAttribute("warehouseList", warehouses.getData());
        model.addAttribute("message", warehouses.getMessage());
        return "admin/warehouse-search";
    }

    @PostMapping("/admin/warehouse/update")
    public String uodateWarehouse(@ModelAttribute AdminWarehouseDto adminWarehouseDto){
        adminWarehouseService.updateWarehouse(adminWarehouseDto);
        return "redirect:/admin/warehouse";
    }
}
