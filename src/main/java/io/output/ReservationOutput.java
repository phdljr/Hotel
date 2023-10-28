package io.output;

import domain.Reservation;
import java.util.List;

public class ReservationOutput {

    private final String TAB = "\t|\t";
    private final String TITLE_PREFIX = "[ 뇌정지 호텔 - ";
    private final String TITLE_SUFFIX = "님 ]\n";

    private final String SELECT_CANCEL = "\n취소하실 예약 번호를 입력해주세요: (예약번호 입력 후 엔터)";
    private final String ZERO_MESSAGE = "메인 화면으로 이동하시려면 0을 눌러주세요.";
    private final String LIST_EMPTY = "현재 확정된 예약이 없습니다. 메인 화면으로 이동합니다.";
    private final String POSSIBLE_LIST_EMPTY = "현재 취소 가능한 예약이 없습니다. 메인 화면으로 이동합니다.";
    private final String RESERVATION_NUMBER = "예약번호: ";
    private final String CHECK_CANCEL_RESERVATION = "예약을 취소하시겠습니까?\n1. 확인\t2. 취소";
    private final String NOT_FOUND_RESERVATION = "입력하신 예약 번호에 맞는 예약이 없습니다. 메인 화면으로 이동합니다.";
    private final String COMPLETE_CANCEL_RESERVATION = "예약이 성공적으로 취소되었습니다.";
    private final String RESERVATION_MAINTAINED = "취소를 선택하셨습니다. 예약은 취소되지 않습니다.";

    public boolean printCancelReservationView(List<Reservation> reservationList, String name) {
        System.out.println(TITLE_PREFIX + name + TITLE_SUFFIX);
        if (reservationList == null || reservationList.isEmpty()) {
            System.out.println(LIST_EMPTY);
            return false;
        }

        //datetime을 확인한 후 리스트를 출력
        int flag = 0;
        for (Reservation reservation : reservationList) {
            if (!reservation.isPassed()) {
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            System.out.println(POSSIBLE_LIST_EMPTY);
            return false;
        }

        reservationList.stream()
            .filter(reservation ->
                !reservation.isPassed())
            .forEach(reservation -> {
                System.out.println(
                    reservation.getRoom().getNumber() + TAB + reservation.getRoom().getRoomType()
                        .getType()
                        + TAB
                        + reservation.getRoom().getCost() + TAB + reservation.getUuid());
            });
        System.out.println(SELECT_CANCEL);
        System.out.println(ZERO_MESSAGE);
        return true;
    }

    public void printCheckCancelReservation(Reservation reservation, String name) {
        System.out.println(TITLE_PREFIX + name + TITLE_SUFFIX);
        System.out.println(
            RESERVATION_NUMBER + reservation.getUuid() + "(\"" + reservation.getRoom().getNumber()
                + TAB + reservation.getRoom().getRoomType().getType() + TAB + reservation.getRoom()
                .getCost()
                + "\")\n");
        System.out.println(CHECK_CANCEL_RESERVATION);
    }

    public void printNotFoundReservation() {
        System.out.println(NOT_FOUND_RESERVATION);
    }

    public void printCompleteCancelReservation(String name) {
        System.out.println(TITLE_PREFIX + name + TITLE_SUFFIX);
        System.out.println(COMPLETE_CANCEL_RESERVATION);
    }

    public void printReservationMaintained() {
        System.out.println(RESERVATION_MAINTAINED);
    }
}
