package io.output;

import domain.Customer;

/**
 * 메인 화면<br/> 일반 고객 메인 또는 관리자 메인 화면이 출력
 */
public class MainOutput {

    public void printCustomerMainView(Customer customer) {
        System.out.printf("[ 뇌정지 호텔 - %s님 ]\n", customer.getName());
        System.out.println("서비스를 선택해 주세요.");
        System.out.println();
        System.out.println("1. 객실 예약");
        System.out.println("2. 객실 예약 취소");
        System.out.println("3. 객실 예약 대기 목록");
        System.out.println("4. 마이 페이지");
        System.out.println("5. 로그아웃");
    }

    public void printAdminMainView(Customer customer) {
        System.out.printf("[ 뇌정지 호텔 - %s님 ]\n", customer.getName());
        System.out.println("서비스를 선택해 주세요.");
        System.out.println();
        System.out.println("1. 모든 예약 목록 조회");
        System.out.println("2. 로그아웃");
    }
}
