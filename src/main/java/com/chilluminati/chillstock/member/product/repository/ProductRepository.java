package com.chilluminati.chillstock.member.product.repository;

import com.chilluminati.chillstock.member.product.dto.ProductDTO;
import com.chilluminati.chillstock.member.product.vo.CategoryMainVO;
import com.chilluminati.chillstock.member.product.vo.CategoryMidVO;
import com.chilluminati.chillstock.member.product.vo.ProductVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductRepository {

    // 신규 제품 등록
    int insertProduct(ProductVO vo);

    // 제품 정보 수정
    int updateProduct(ProductVO vo);

    // 제품 ID로 단건 조회
    ProductVO selectProductById(int productId);
    int deleteProduct(int productId);

    //  내 제품 리스트 조회 (입고 요청 화면 등에서 사용)
    List<ProductVO> selectMyPagedProducts( @Param("userId") Integer userId,
                                           @Param("limit") int limit,
                                           @Param("offset") int offset);
    // 사용자의 전체 제품 개수 조회
    int countMyProducts(@Param("userId") Integer userId);


    //전체 대분류 카테고리 조회
    List<CategoryMainVO> selectAllMainCategories();

    //전체 중분류 카테고리 조회
    List<CategoryMidVO> selectAllMidCategories();
}
