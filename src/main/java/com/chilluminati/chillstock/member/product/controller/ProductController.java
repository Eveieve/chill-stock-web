package com.chilluminati.chillstock.member.product.controller;


import com.chilluminati.chillstock.member.product.dto.CategoryMainDTO;
import com.chilluminati.chillstock.member.product.dto.CategoryMidDTO;
import com.chilluminati.chillstock.member.product.dto.ProductDTO;
import com.chilluminati.chillstock.member.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class ProductController {

    private final ProductService productService;

    /**
     * 제품 페이지 회원이 등록된 제품 뿌려주며 보이기
     */
    @GetMapping("product-register")
    public String showRegisterForm(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 10;

        int total = productService.getMyProductCount();
        int totalPages = (int) Math.ceil((double) total / pageSize );

        if(page < 1) page = 1; // 나중에 람다로 할 것
        if(page > totalPages) page = totalPages;

        List<ProductDTO> products = productService.getMyPagedProducts(page, pageSize);

        model.addAttribute("productDTO", new ProductDTO());
        // 대분류 중분류 리스트 넘겨주기
        List<CategoryMainDTO> allMainCategories = productService.getAllMainCategories();
        List<CategoryMidDTO> allMidCategories = productService.getAllMidCategories();

        // ✅ 로그 찍기 (혹시 null이나 []인지 확인용)
        System.out.println("대분류: " + allMainCategories);
        System.out.println("중분류: " + allMidCategories);

        model.addAttribute("mainCategories", allMainCategories);
        model.addAttribute("midCategories", allMidCategories);

        model.addAttribute("pageName", "product-register");
        model.addAttribute("products", products);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);

        return "member/product-register";
    }

    /**
     * 제품 등록 처리
     */
    @PostMapping("product-register")
    public String registerProduct(@ModelAttribute("productDTO") ProductDTO productDTO, RedirectAttributes redirectAttributes) {

        productService.registerProduct(productDTO);
        redirectAttributes.addFlashAttribute("successMessage", "제품이 등록되었습니다.");
        return "redirect:/member/product-register";

    }

    /**
     * 제품 삭제 처리
     */
    @PostMapping("product-delete")
    public String deleteProducts(@RequestParam("productIdList") List<Integer> productIdList, RedirectAttributes redirectAttributes) {

        if (productIdList == null || productIdList.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "삭제할 제품을 선택해주세요.");
            return "redirect:/member/product-register";
        }

        for (Integer id : productIdList) {
            productService.deleteProduct(id);
        }

        redirectAttributes.addFlashAttribute("successMessage", "선택한 제품이 삭제되었습니다.");
        return "redirect:/member/product-register";
    }

    /**
     * 제품 수정 처리
     */
    @PostMapping("/product-update")
    public String updateProduct(@ModelAttribute ProductDTO dto, RedirectAttributes redirectAttributes) {
        productService.updateProduct(dto);
        redirectAttributes.addFlashAttribute("successMessage","제품이 수정되었습니다.");
        return "redirect:/member/product-register";
    }



}
