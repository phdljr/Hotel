package business;

import domain.Customer;
import domain.CustomerType;
import domain.Reservation;
import io.input.InputView;
import io.output.BasketOutput;
import io.output.OutputView;
import io.output.ReservationOutput;
import java.util.List;
import java.util.Map;
import service.BasketService;
import service.CustomerService;
import service.ReservationService;

/**
 * 서비스 클래스 또는 InputView(사용자로부터의 입력)로부터 데이터를 요청받아서 OutputView 클래스로 보내주는 역할
 */
public class HotelLounge {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final BasketOutput basketOutput = new BasketOutput();
    private final CustomerService customerService = new CustomerService();
    private final BasketService basketService = new BasketService();
    private final ReservationService reservationService = new ReservationService();
    private final ReservationOutput reservationOutput = new ReservationOutput();

    private Customer customer;

    public void start() {
        while (true) {
            outputView.printInitView();
            int inputNumber = inputView.getInputNumber(1, 2);

            switch (inputNumber) {
                case 1:
                    showLoginView(); // 로그인 화면 보여주기
                    break;
                case 2:
                    showSignUpView(); // 회원가입
                    break;
            }

            // 로그인 또는 회원가입을 하고 난 뒤
            showMainView();
        }
    }

    /**
     * 로그인 화면 출력 이 메소드가 호출되고나서 종료되면, 로그인 된걸로 간주
     */
    private void showLoginView() {
        outputView.printLoginView();
        while (true) {
            String id = inputView.getInputString();
            if (customerService.contains(id)) {
                customer = customerService.login(id);
                return;
            } else {
                outputView.printWrongInputId();
            }
        }
    }

    /**
     * 회원가입 화면 출력
     */
    private void showSignUpView() {
        String id = "";
        String name = "";
        String phoneNumber = "";
        long money = 0L;

        int step = 1;
        outputView.printSignUpTitleView();
        while (step != 5) {
            outputView.printSignUpView(step);
            switch (step) {
                case 1:
                    id = inputView.getInputString();
                    if (customerService.contains(id)) {
                        outputView.printAlreadyExistId();
                        step--;
                    }
                    step++;
                    break;
                case 2:
                    name = inputView.getInputString();
                    step++;
                    break;
                case 3:
                    phoneNumber = inputView.getInputPhoneNumber();
                    step++;
                    break;
                case 4:
                    money = inputView.getInputMoney();
                    step++;
                    break;
            }
        }
        customer = customerService.signUp(id, name, phoneNumber, money);
    }

    /**
     * 로그인한 고객의 유형을 보고 출력할 메인 화면을 선택
     */
    private void showMainView() {
        // 일반 손님이라면
        if (customer.getCustomerType() == CustomerType.CUSTOMER) {
            showCustomerMainView();
        } else {
            showAdminMainView();
        }
        outputView.printLogoutCommentView();
    }

    /**
     * 일반 손님일 경우의 메인 화면 출력
     */
    private void showCustomerMainView() {
        boolean flag = true;
        while (flag) {
            outputView.printCustomerMainView(customer);
            int inputNumber = inputView.getInputNumber(1, 5);
            switch (inputNumber) {
                case 1:
                    break;
                case 2:
                    showCancelReservationView();
                    break;
                case 3: //장바구니 화면
                    showBasketView();
                    break;
                case 4:
                    showMyPage();
                    break;
                case 5:
                    flag = showLogoutView();
                    break;
            }
        }
    }

    private void showCancelReservationView() {
        Map<String, Reservation> reservationMap
            = reservationService.getReservationMap(customer.getId());

        if (!reservationOutput.printCancelReservationView(
            reservationMap.values().stream().toList(), customer.getName())) {
            return;
        }

        String inputReservationUuid = inputView.getReservationUuid();
        if ("0".equals(inputReservationUuid)) {
            return;
        }

        if (reservationMap.containsKey(inputReservationUuid)) {
            showCheckCancelReservationView(reservationMap, inputReservationUuid);
        } else {
            reservationOutput.printNotFoundReservation();
        }
    }

    private void showCheckCancelReservationView(
        Map<String, Reservation> reservationMap, String inputReservationUuid) {
        reservationOutput.printCheckCancelReservation(
            reservationMap.get(inputReservationUuid), customer.getName());

        int pick = inputView.getInputNumber(1, 2);
        if (pick == 1) {
            reservationService.removeReservation(inputReservationUuid);
            reservationOutput.printCompleteCancelReservation(customer.getName());
            waitForThreeSec();
        } else {
            reservationOutput.printReservationMaintained();
        }
    }

    // -------장바구니 화면------------
    private void showBasketView() {
        boolean basketFlag = true;
        while (basketFlag) {
            basketOutput.printBasketTitle(customer.getName());
            if (basketService.returnBasketList().isEmpty()) {
                basketOutput.haveEmptyBasket();
                waitForThreeSec();
                break;
            }
            basketOutput.printBasketText();
            basketOutput.printBasketList(basketService.returnBasketList());
            basketOutput.checkBasketMenu();
            int input = inputView.getInputNumber(1, 3);
            switch (input) {
                case 1: //예약 진행
                    basketFlag = showReserveBasketMainView();
                    break;
                case 2: //예약 취소
                    basketFlag = showDeleteBasketMainView();
                    break;
                case 3: //메인 메뉴로 돌아가기
                    basketFlag = false;
                    break;
            }
        }
    }

    private boolean showReserveBasketMainView() { // 장바구니 1번 선택 화면
        basketOutput.printBasketTitle(customer.getName());
        basketOutput.printBasketList(basketService.returnBasketList());
        basketOutput.printBasketPrice(basketService.returnBasketPrice());
        basketOutput.reserveBasket();
        int reserveConfirm = inputView.getInputNumber(1, 2);
        if (reserveConfirm == 1) {
            if (basketService.checkMoney(customer)) {
                basketService.makeBasketToReservation(customer);
                basketOutput.successReserveBasket();
            } else {
                basketOutput.failureReserveBasket();
            }
            waitForThreeSec();
            return false;
        }
        return true;
    }

    private boolean showDeleteBasketMainView() { // 장바구니 2번 선택 화면
        basketOutput.printBasketTitle(customer.getName());
        basketOutput.deleteBasket();
        basketOutput.printBasketList(basketService.returnBasketList());
        int index = inputView.getInputNumber(1,
            basketService.returnBasketList().size()) - 1;
        basketOutput.printBasketTitle(customer.getName());
        basketOutput.confirmDeleteBasket(
            basketService.returnRoomToCancel(index));
        int cancelConfirm = inputView.getInputNumber(1, 2);
        if (cancelConfirm == 1) {
            basketService.deleteRoomFromBasket(index);
            basketOutput.successDeleteBasket();
            waitForThreeSec();
            return false;
        }
        return true;
    }

    public void waitForThreeSec() { //3초 뒤 메인화면으로 돌아가는 메소드
        System.out.println("3초 뒤에 메인 화면으로 이동합니다.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // -------장바구니 화면 끝------------

    /**
     * 관리자일 경우의 메인 화면 출력
     */
    private void showAdminMainView() {
        boolean flag = true;
        while (flag) {
            outputView.printAdminMainView(customer);
            int inputNumber = inputView.getInputNumber(1, 2);
            switch (inputNumber) {
                case 1:
                    showReserveAllRoom();
                    break;
                case 2:
                    flag = showLogoutView();
                    break;
            }
        }
    }

    private void showReserveAllRoom() {
        List<Reservation> reservations = reservationService.getAllReservation();
        outputView.printReserveAllRoom(customer, reservations);
        inputView.getInputNumber(1, 1);
    }

    private void showMyPage() {
        outputView.printMyPageView(customer);
        inputView.getInputNumber(1, 1);
    }

    private boolean showLogoutView() {
        outputView.printLogoutView(customer);
        int inputNumber = inputView.getInputNumber(1, 2);

        return inputNumber == 2; // 취소를 선택할 때
    }
}
