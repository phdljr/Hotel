import domain.Customer;
import org.junit.jupiter.api.Test;
import service.CustomerService;

import static org.assertj.core.api.Assertions.assertThat;

public class SignUpTest {
    private final CustomerService customerService = new CustomerService();

    @Test
    public void signUpTest(){
        // given
        String id = "test111";
        String name = "테스트";
        String phoneNumber = "123-4567-8901";
        customerService.signUp(id, name, phoneNumber, 10000000);

        // when
        Customer customer = customerService.login(id);

        // then
        assertThat(customer).extracting("id").isEqualTo(id);
    }
}
