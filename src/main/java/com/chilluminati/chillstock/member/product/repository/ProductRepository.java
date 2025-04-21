package com.chilluminati.chillstock.member.product.repository;

import com.chilluminati.chillstock.member.product.dto.ProductDTO;
import com.chilluminati.chillstock.member.product.vo.ProductVO;

import java.util.List;

public interface ProductRepository {
    int insertProduct(ProductDTO dto);
    int updateProduct(ProductDTO dto);
    ProductVO selectProductById(int productId);
    int deleteProduct(int productId);

    //  내 제품 리스트 조회 (입고 요청 화면 등에서 사용)
    List<ProductVO> selectMyProducts(Integer userId);
}
