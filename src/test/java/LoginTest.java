import data.CustomerDatabase;
import domain.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.CustomerService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class LoginTest {

    private final CustomerService customerService = new CustomerService();

    @Test
    @DisplayName("로그인 성공")
    public void loginSuccessTest(){
        // given

        // when
        Customer admin = customerService.findById("admin");

        // then
        assertThat(admin).extracting("id").isEqualTo("admin");
    }

    @Test
    @DisplayName("로그인 실패")
    public void loginFailTest(){
        // given

        // when
        Throwable thrown = catchThrowable(() -> customerService.findById("admin123"));

        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("어드민 권한을 가졌는지 확인")
    public void isAdminTest(){
        // given

        // when
        Customer admin = customerService.findById("admin");
        boolean result = customerService.isAdmin(admin);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("일반 손님 권한을 가졌는지 확인")
    public void isCustomerTest(){
        // given

        // when
        Customer admin = customerService.findById("test1");
        boolean result = customerService.isAdmin(admin);

        // then
        assertThat(result).isEqualTo(false);
    }
}
