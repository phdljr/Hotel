package business;

import domain.Customer;
import domain.CustomerType;
import io.input.InputView;
import io.output.OutputView;
import service.CustomerService;

/**
 * 서비스 클래스 또는 InputView(사용자로부터의 입력)로부터 데이터를 요청받아서 OutputView 클래스로 보내주는 역할
 */
public class HotelLounge {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final CustomerService customerService = new CustomerService();

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
                case 4:
                    showMyPage();
                    break;
                case 5:
                    flag = showLogoutView();
                    break;
            }
        }
    }

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

                    break;
                case 2:
                    showLogoutView();
                    break;
            }
        }
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
