package com.chilluminati.chillstock.member.product.service;

import com.chilluminati.chillstock.member.product.dto.CategoryMainDTO;
import com.chilluminati.chillstock.member.product.dto.CategoryMidDTO;
import com.chilluminati.chillstock.member.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    void registerProduct(ProductDTO dto);
    void updateProduct(ProductDTO dto);
    void deleteProduct(int productId);

    // 로그인한 사용자의 제품 리스트 조회
    List<ProductDTO> getMyPagedProducts(int page, int pageSize);

    int getMyProductCount();



    List<CategoryMainDTO> getAllMainCategories();

    List<CategoryMidDTO> getAllMidCategories();
}
