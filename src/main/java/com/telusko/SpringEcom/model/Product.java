package com.telusko.SpringEcom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity // DB 테이블과 1:1로 매핑되는 객체 선언
@Data // Getter, Setter, toString 등 필수 메서드 자동 생성
@NoArgsConstructor // 파라미터 없는 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 사용하는 생성자 생성
public class Product {
    @Id // 테이블의 기본 키(PK) 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 번호 자동 생성(Auto Increment)
    private int id; // 상품 고유 식별 번호

    private String name; // 상품명 저장
    private String description; // 상품 상세 설명 저장
    private String brand; // 제조사/브랜드 정보 저장
    private BigDecimal price; // 상품 가격 저장 (정밀한 소수점 계산용)
    private String category; // 상품 분류(카테고리) 저장
    private Date releaseDate; // 상품 출시일 저장
    private boolean productAvailable; // 판매 가능 여부(품절 여부) 체크
    private int stockQuantity; // 현재 남은 재고 수량 관리

    private String imageName; // 업로드된 이미지 파일명 저장
    private String imageType; // 이미지 확장자(jpg, png 등) 저장

    @Lob // 대용량 데이터(Large Object) 처리를 위한 선언
    private byte[] imageData; // 이미지 파일의 이진 데이터(Binary) 직접 저장

    // 특정 ID만 가진 객체를 생성하기 위한 편의 생성자
    public Product(int id) {
        this.id = id; // ID 값 강제 할당
    }
}