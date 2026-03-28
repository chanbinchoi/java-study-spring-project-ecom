package com.telusko.SpringEcom.service;

import com.telusko.SpringEcom.model.Product;
import com.telusko.SpringEcom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service // 비즈니스 로직을 수행하는 서비스 객체임을 선언
public class ProductService {

    @Autowired // 데이터베이스 접근을 위한 레포지토리 주입
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {
        // 데이터베이스의 모든 상품 목록 반환
        return productRepo.findAll();
    }

    public Product getProductById(int id) {
        // ID로 상품 검색, 없을 경우 null 반환 처리
        return productRepo.findById(id).orElse(null);
    }

    public Product addOrUpdateProduct(Product product, MultipartFile image) throws IOException {
        // 업로드된 이미지의 원본 파일명 저장
        product.setImageName(image.getOriginalFilename());
        // 이미지의 파일 형식(jpg, png 등) 저장
        product.setImageType(image.getContentType());
        // 이미지 파일을 0과 1의 이진 데이터(byte[])로 변환하여 저장
        product.setImageData(image.getBytes());

        // 가공된 상품 객체를 데이터베이스에 저장 및 결과 반환
        return productRepo.save(product);
    }

    public void deleteProduct(int id) {
        // 해당 ID를 가진 상품 삭제 실행
        productRepo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        // 키워드를 통한 맞춤형 상품 검색 실행
        return productRepo.searchProducts(keyword);
    }
}