package com.chilluminati.chillstock.member.product.service;

import com.chilluminati.chillstock.member.product.dto.CategoryMainDTO;
import com.chilluminati.chillstock.member.product.dto.CategoryMidDTO;
import com.chilluminati.chillstock.member.product.dto.ProductDTO;
import com.chilluminati.chillstock.member.product.repository.ProductRepository;
import com.chilluminati.chillstock.member.product.vo.CategoryMainVO;
import com.chilluminati.chillstock.member.product.vo.CategoryMidVO;
import com.chilluminati.chillstock.member.product.vo.ProductVO;
import com.chilluminati.chillstock.security.EmailUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void registerProduct(ProductDTO dto) {
        Integer userId = getInteger();
        ProductVO productVO = ProductVO.builder()
                .productName(dto.getProductName())
                .productSize(dto.getProductSize())
                .storageTemperature(dto.getStorageTemperature())
                .expirationDate(dto.getExpirationDate())
                .categoryMidId(dto.getCategoryMidId())
                .userId(userId)
                .build();
        productRepository.insertProduct(productVO);

    }

    @Override
    public void updateProduct(ProductDTO dto) {
        ProductVO vo = ProductVO.builder()
                .productId(dto.getProductId())
                .productName(dto.getProductName())
                .productSize(dto.getProductSize())
                .storageTemperature(dto.getStorageTemperature())
                .expirationDate(dto.getExpirationDate())
                .categoryMidId(dto.getCategoryMidId())
                .build();


        productRepository.updateProduct(vo);
    }

    @Override
    public void deleteProduct(int productId) {

        productRepository.deleteProduct(productId);
    }

    @Override
    public List<ProductDTO> getMyPagedProducts(int page, int pageSize) {
        Integer userId = getInteger();

        int offset = (page - 1) * pageSize; // 함수형인터페이스를 뺴서

        List<ProductVO> voList = productRepository.selectMyPagedProducts(userId, pageSize, offset);

        return voList.stream()
                .map(vo -> ProductDTO.builder()
                        .productId(vo.getProductId())
                        .productName(vo.getProductName())
                        .productSize(vo.getProductSize())
                        .storageTemperature(vo.getStorageTemperature())
                        .expirationDate(vo.getExpirationDate())
                        .categoryName(vo.getCategoryName())
                        .subCategoryName(vo.getSubCategoryName())
                        .build())
                .collect(Collectors.toList());
    }


    @Override
    public int getMyProductCount() {
        Integer userId = getInteger();
        return productRepository.countMyProducts(userId);
    }

    @Override
    public List<CategoryMainDTO> getAllMainCategories() {
        List<CategoryMainVO> voList = productRepository.selectAllMainCategories();
        return voList.stream()
                .map(vo -> CategoryMainDTO.builder()
                        .categoryId(vo.getCategoryId())
                        .categoryName(vo.getCategoryName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryMidDTO> getAllMidCategories() {
        List<CategoryMidVO> voList = productRepository.selectAllMidCategories();
        return voList.stream()
                .map(vo -> CategoryMidDTO.builder()
                        .categoryMidId(vo.getCategoryMidId())
                        .categoryName(vo.getCategoryName())
                        .categoryMainId(vo.getCategoryMainId())
                        .build()).collect(Collectors.toList());
    }

    private static Integer getInteger() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmailUserDetails userDetails = (EmailUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getUserId();
        return userId;
    }
}
