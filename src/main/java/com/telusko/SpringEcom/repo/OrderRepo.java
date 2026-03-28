package com.telusko.SpringEcom.repo;

import com.telusko.SpringEcom.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // 스프링의 데이터 접근 계층(저장소)임을 선언
public interface OrderRepo extends JpaRepository<Order, Integer> {
    // Order 엔티티를 관리하며 ID 타입은 Integer임

    // 주문 번호(orderId)를 이용한 데이터 조회 메서드 정의
    Optional<Order> findByOrderId(String orderId);
    // 검색 결과가 없을 수도 있으므로 Optional로 감싸서 반환함
}