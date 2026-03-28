package com.telusko.SpringEcom.controller;

import com.telusko.SpringEcom.model.dto.OrderRequest;
import com.telusko.SpringEcom.model.dto.OrderResponse;
import com.telusko.SpringEcom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // JSON 형태로 데이터를 반환하는 컨트롤러 선언
@RequestMapping("/api") // 모든 요청 주소 앞에 /api를 붙임
@CrossOrigin // 외부 도메인에서 이 서버로의 접근 허용
public class OrderController {

    @Autowired // 필요한 서비스 객체를 스프링이 자동으로 주입함
    private OrderService orderService;

    @PostMapping("/orders/place") // POST 방식의 /api/orders/place 요청 매핑
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
        // 서비스의 주문 처리 메서드 호출 및 결과 저장
        OrderResponse orderResponse = orderService.placeOrder(orderRequest);
        // 상태 코드 201(Created)과 함께 결과 데이터 반환
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @GetMapping("/orders") // GET 방식의 /api/orders 요청 매핑
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        // 서비스에서 전체 주문 목록 데이터 조회
        List<OrderResponse> responses = orderService.getAllOrdersResponse();
        // 상태 코드 200(OK)과 함께 목록 데이터 반환
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}