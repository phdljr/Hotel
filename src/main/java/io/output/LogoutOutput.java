package io.output;

import domain.Customer;

/**
 * 로그아웃 화면
 */
public class LogoutOutput {

    public void printLogoutView(Customer customer) {
        System.out.printf("[ 뇌정지 호텔 - %s님 ]\n", customer.getName());
        System.out.println("로그아웃을 하시겠습니까?");
        System.out.println();
        System.out.println("1. 확인\t\t2. 취소");
    }

    public void printLogoutCommentView() {
        System.out.println("로그아웃이 정상적으로 처리되었습니다.");
        System.out.println();
    }
}
