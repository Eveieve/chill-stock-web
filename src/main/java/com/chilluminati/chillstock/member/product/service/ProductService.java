package com.chilluminati.chillstock.member.product.service;

import com.chilluminati.chillstock.member.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    void registerProduct(ProductDTO dto);
    void updateProduct(ProductDTO dto);
    void deleteProduct(int productId);

    // 로그인한 사용자의 제품 리스트 조회
    List<ProductDTO> getMyProducts();
}
