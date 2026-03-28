package com.telusko.SpringEcom.controller;

import com.telusko.SpringEcom.model.Product;
import com.telusko.SpringEcom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController // JSON 반환용 컨트롤러 선언
@RequestMapping("/api") // 공통 경로 설정
@CrossOrigin // 외부 도메인 접근 허용
public class ProductController {

    @Autowired // 비즈니스 로직 담당 서비스 주입
    private ProductService productService;

    @GetMapping("/products") // 전체 상품 목록 조회 요청 처리
    public ResponseEntity<List<Product>> getProducts() {
        // 모든 상품 데이터를 리스트로 반환
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}") // 특정 ID 상품 상세 조회
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        // 상품 존재 여부 확인 후 적절한 상태 코드 반환
        if (product.getId() > 0)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("product/{productId}/image") // 특정 상품의 이미지 데이터만 조회
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {
        Product product = productService.getProductById(productId);
        // 이미지 바이트 배열 데이터를 HTTP 바디에 실어서 전송
        if (product.getId() > 0)
            return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product") // 새 상품과 이미지를 동시에 등록
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
        try {
            // 정보와 파일을 서비스에 전달하여 저장 처리
            Product savedProduct = productService.addOrUpdateProduct(product, imageFile);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (IOException e) {
            // 파일 처리 중 에러 발생 시 500 에러 반환
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product/{id}") // 기존 상품 정보 및 이미지 수정
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile) {
        try {
            // 수정된 정보 반영 요청
            productService.addOrUpdateProduct(product, imageFile);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{id}") // 상품 삭제 처리
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            // 서비스에 삭제 명령 전달
            productService.deleteProduct(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/product/search") // 키워드 기반 상품 검색
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        // 검색어와 일치하는 상품 목록 조회
        List<Product> products = productService.searchProducts(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}