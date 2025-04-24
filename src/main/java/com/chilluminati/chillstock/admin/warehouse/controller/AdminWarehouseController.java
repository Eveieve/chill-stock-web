package com.chilluminati.chillstock.admin.warehouse.controller;


import com.chilluminati.chillstock.admin.warehouse.dto.AdminWarehouseDto;
import com.chilluminati.chillstock.admin.warehouse.service.AdminWarehouseService;
import com.chilluminati.chillstock.admin.warehouse.vo.AdminWarehouseVo;
import com.chilluminati.chillstock.common.ResultList;
import com.chilluminati.chillstock.webclient.GeoPoint;
import com.chilluminati.chillstock.webclient.GeoService;
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

    @GetMapping("admin/warehouse/remain")
    public String adminWarehouseRemain(Model model) {
        model.addAttribute("warehouseList", adminWarehouseService.getAllWarehousesWithRemain());
        return "admin/warehouse-remain";
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

    @GetMapping("/admin/warehouse/detail")
    public String detailWarehouse(@RequestParam Integer warehouseId, Model model) {
        AdminWarehouseDto adminWarehouseById = adminWarehouseService.getAdminWarehouseById(warehouseId);
        Integer remainSpace = adminWarehouseService.getAdminWarehouseRemainSpaceById(warehouseId).getWarehouseSpaceRemain();
        model.addAttribute("warehouse", adminWarehouseById);
        model.addAttribute("remainSpace", remainSpace);
        return "admin/warehouse-detail";
    }

    //test
    @RestController
    @RequestMapping("/admin/geo")
    @RequiredArgsConstructor
    public class GeoController {

        private final GeoService geoService;

        @GetMapping
        public GeoPoint getGeo(@RequestParam String address) {
            return geoService.getGeoByAddress(address)
                    .orElseThrow(() -> new RuntimeException("오오류우"));
        }
    }
}
