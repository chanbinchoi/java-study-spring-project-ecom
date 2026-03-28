package com.telusko.SpringEcom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "orders") // 데이터베이스의 orders 테이블과 매핑함
@Data // Getter, Setter, toString 등 자동 생성
@NoArgsConstructor // 파라미터 없는 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 포함하는 생성자 생성
public class Order {
    @Id // 테이블의 기본 키(Primary Key) 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 ID 자동 증가 처리
    private Long id; // 시스템 내부 관리용 고유 번호

    @Column(unique = true) // 중복 값을 허용하지 않는 고유 컬럼 설정
    private String orderId; // 사용자에게 노출되는 주문 식별 번호

    private String customerName; // 주문 고객 성함 저장
    private String email; // 고객 이메일 주소 저장
    private String status; // 주문 처리 상태 저장
    private LocalDate orderDate; // 주문이 발생한 날짜 저장

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // 일대다 관계 설정 및 영속성 전이 적용
    private List<OrderItem> orderItems; // 하나의 주문에 속한 여러 상세 상품 목록
}