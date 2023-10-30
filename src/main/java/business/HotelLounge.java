package business;

import domain.Customer;
import domain.CustomerType;
import domain.Reservation;
import domain.Room;
import io.input.InputView;
import io.output.AdminOutput;
import io.output.BasketOutput;
import io.output.EnterOutput;
import io.output.LoginOutput;
import io.output.LogoutOutput;
import io.output.MainOutput;
import io.output.MyPageOutput;
import io.output.ReservationOutput;
import io.output.RoomCheckOutput;
import io.output.SignUpOutput;
import java.util.List;
import java.util.Map;
import service.BasketService;
import service.CustomerService;
import service.ReservationService;
import service.RoomService;

/**
 * 서비스 클래스 또는 InputView(사용자로부터의 입력)로부터 데이터를 요청받아서 OutputView 클래스로 보내주는 역할
 */
public class HotelLounge {

    private final InputView inputView = new InputView();
    private final BasketOutput basketOutput = new BasketOutput();
    private final ReservationOutput reservationOutput = new ReservationOutput();
    private final EnterOutput enterOutput = new EnterOutput();
    private final MyPageOutput myPageOutput = new MyPageOutput();
    private final LoginOutput loginOutput = new LoginOutput();
    private final LogoutOutput logoutOutput = new LogoutOutput();
    private final MainOutput mainOutput = new MainOutput();
    private final SignUpOutput signUpOutput = new SignUpOutput();
    private final AdminOutput adminOutput = new AdminOutput();

    private final CustomerService customerService = new CustomerService();
    private final BasketService basketService = new BasketService();
    private final ReservationService reservationService = new ReservationService();
    private final RoomService roomService = new RoomService();
    private final RoomCheckOutput roomCheckOutput = new RoomCheckOutput();
    private Customer customer;

    //1초 대기를 위한 스레드 sleep
    private void oneSecHold() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            enterOutput.printInitView();
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
        loginOutput.printLoginView();
        while (true) {
            String id = inputView.getInputString();
            if (customerService.contains(id)) {
                customer = customerService.login(id);
                return;
            } else {
                loginOutput.printWrongInputId();
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
        signUpOutput.printSignUpTitleView();
        while (step != 5) {
            signUpOutput.printSignUpView(step);
            switch (step) {
                case 1:
                    id = inputView.getInputString();
                    if (customerService.contains(id)) {
                        signUpOutput.printAlreadyExistId();
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
        logoutOutput.printLogoutCommentView();
    }

    /**
     * 일반 손님일 경우의 메인 화면 출력
     */
    private void showCustomerMainView() {
        roomService.resetReserved(); //시간 체크 후 reserved 변경
        boolean flag = true;
        while (flag) {
            mainOutput.printCustomerMainView(customer);
            int inputNumber = inputView.getInputNumber(1, 5);
            switch (inputNumber) {
                case 1: //객실선택, 장바구니에 담기
                    showSelectRoomView();
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
            reservationService.removeReservation(
                reservationMap.get(inputReservationUuid), customer);
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

    // ------- 준 예약 (객실 장바구니에 담기) -------시작
    private void showSelectRoomView() {
        Map<Integer, Room> roomList = roomService.getRoomList();
        int roomLength = roomList.size();

        roomCheckOutput.printSelectRoomView(customer, roomList);

        int inputNumber = inputView.getInputNumber(0, roomLength);
        Room selectRoom = roomList.get(inputNumber);
        if (inputNumber == 1) {
            if (selectRoom.isReserved()) {
                showAlreadyReservedRoomView();//"이미 예약됬습니다."
                oneSecHold();//1초 대기
                showSelectRoomView();//선택화면 다시 출력
            } else {
                showCheckRoomView(selectRoom); //확인 후 장바구니에 담을지 선택
            }
        }
    }

    //이미 예약된 객실이라고 알리는 화면
    private void showAlreadyReservedRoomView() {
        roomCheckOutput.printAlreadyReservedRoomView();
    }

    //선택한 객실을 예약할 것인지에 대한 로직 취소할 시, 메인 화면으로 이동
    private void showCheckRoomView(Room selectRoom) {
        roomCheckOutput.printRoomInfo(selectRoom); //선택한 객실 확인 메세지

        int inputNumber = inputView.getInputNumber(1, 2);
        if (inputNumber == 1) {
            // 확인
            basketService.addRoom(selectRoom); // 장바구니로 선택된 객실을 옮기는 메서드
            showConfirmedCheckedRoomView(); // "성공적으로 장바구니에 담겼습니다."
            waitForThreeSec();  // 3초 sleep
        } else {
            // 취소
            showCancelReserveRoomView(); // "취소하였습니다. 메인화면으로 돌아갑니다."
        }
    }

    //객실 선택 확인 완료 화면
    private void showConfirmedCheckedRoomView() {
        roomCheckOutput.printConfirmedCheckedRoomView();
    }

    //객실 선택 확인 취소 화면
    private void showCancelReserveRoomView() {
        roomCheckOutput.printCancelReserveRoomView();
    }
    // ------- 준 예약 끝(객실 장바구니에 담기) -------끝

    /**
     * 관리자일 경우의 메인 화면 출력
     */
    private void showAdminMainView() {
        boolean flag = true;
        while (flag) {
            mainOutput.printAdminMainView(customer);
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
        adminOutput.printReserveAllRoom(customer, reservations);
        inputView.getInputNumber(1, 1);
    }

    private void showMyPage() {
        Map<String, Reservation> reservationMap = reservationService.getReservationMap(
            customer.getId());
        myPageOutput.printMyPageView(customer, reservationMap);
        inputView.getInputNumber(1, 1);
    }

    private boolean showLogoutView() {
        logoutOutput.printLogoutView(customer);
        int inputNumber = inputView.getInputNumber(1, 2);

        return inputNumber == 2; // 취소를 선택할 때
    }
}
