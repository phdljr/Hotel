import data.CustomerDatabase;
import domain.Customer;
import org.junit.jupiter.api.Test;
import service.CustomerService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class LoginTest {

    private final CustomerDatabase customerDatabase = new CustomerDatabase();
    private final CustomerService customerService = new CustomerService(customerDatabase);

    @Test
    public void loginSuccessTest(){
        // given

        // when
        Customer admin = customerService.findById("admin");

        // then
        assertThat(admin).extracting("id").isEqualTo("admin");
    }

    @Test
    public void loginFailTest(){
        // given

        // when
        Throwable thrown = catchThrowable(() -> customerService.findById("admin123"));

        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void isAdminTest(){
        // given

        // when
        Customer admin = customerService.findById("admin");
        boolean result = customerService.isAdmin(admin);

        // then
        assertThat(result).isEqualTo(true);
    }
}
