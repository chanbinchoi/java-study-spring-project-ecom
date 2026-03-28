package com.telusko.SpringEcom.repo;

import com.telusko.SpringEcom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 데이터베이스 접근 계층(저장소) 정의
public interface ProductRepo extends JpaRepository<Product, Integer> {
    // 기본 CRUD 기능(저장, 수정, 삭제, ID 조회) 상속

    @Query("SELECT p from Product p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
        // JPQL을 사용한 맞춤형 검색 쿼리 작성
        // 이름, 설명, 브랜드, 카테고리 중 하나라도 키워드를 포함하면 조회함
        // 대소문자 구분 없이 검색하기 위해 LOWER 함수 적용
    List<Product> searchProducts(String keyword);
}