package com.telusko.SpringEcom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity // DB 테이블 매핑 선언
@Data // Getter, Setter 자동 생성
@NoArgsConstructor // 기본 생성자 자동 생성
@AllArgsConstructor // 모든 필드 생성자 생성
@Builder // 객체 생성을 도와주는 빌더 패턴 적용
public class OrderItem {
    @Id // 기본 키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID 값 자동 증가
    private int id; // 항목 자체의 고유 식별 번호

    @ManyToOne // 여러 항목이 하나의 상품을 가리키는 다대일 관계
    private Product product; // 연결된 상품 정보

    private int quantity; // 구매 수량
    private BigDecimal totalPrice; // 해당 항목의 총 금액 (단가 * 수량)

    @ManyToOne(fetch = FetchType.LAZY) // 여러 항목이 하나의 주문에 속하는 다대일 관계
    private Order order; // 소속된 주문 정보
}