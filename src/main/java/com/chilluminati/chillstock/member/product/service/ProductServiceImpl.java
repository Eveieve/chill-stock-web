package com.chilluminati.chillstock.member.product.service;

import com.chilluminati.chillstock.member.product.dto.ProductDTO;
import com.chilluminati.chillstock.member.product.repository.ProductRepository;
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

    }

    @Override
    public void updateProduct(ProductDTO dto) {

    }

    @Override
    public void deleteProduct(int productId) {

    }

    @Override
    public List<ProductDTO> getMyProducts() { //옵셔널로 받아오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmailUserDetails userDetails = (EmailUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getUserId();

        List<ProductVO> voList = productRepository.selectMyProducts(userId);

        return  voList.stream()
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
}
